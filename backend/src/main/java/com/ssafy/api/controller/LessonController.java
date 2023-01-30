package com.ssafy.api.controller;

import com.ssafy.api.request.LessonRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.LessonService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.lesson.Checklist;
import com.ssafy.db.entity.lesson.Curriculum;
import com.ssafy.db.entity.lesson.Lesson;
import com.ssafy.db.entity.user.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "강의 API", tags = {"Lesson"})
@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    UserService userService;
    @PostMapping()
    @ApiOperation(value = "강의 등록", notes = "<strong>강의 정보</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value = "강의 등록 정보", required = true) LessonRegisterPostReq requestInfo) {
        /*[lessonInfo]
            1. 강사 이메일
            2. 강의명
            3. 강의 소개
            4. List<강의 소개 사진>
            5. List<준비물*(사진, 설명)> 등록
            6. List<커리큘럼> 등록
            7. 예상 강의 시간( 강의 소요 시간 )
            8. 참여 가능 인원
            9. 강의 가격
        */
        User user = userService.getUserByAuth(requestInfo.getEmail());
        if(user == null) return ResponseEntity.status(404).body(BaseResponseBody.of(404, "사용자 없음"));

        lessonService.createLesson(requestInfo.getLessonInfoFromReq(user));

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
