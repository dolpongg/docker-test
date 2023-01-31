package com.ssafy.api.controller;

import com.ssafy.api.response.LessonListGetRes;
import com.ssafy.api.service.LessonService;
import com.ssafy.api.service.StudentService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.lesson.Lesson;
import com.ssafy.db.entity.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 수강생 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "수강생 API", tags = {"Student"})
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;
    @Autowired
    LessonService lessonService;
    @GetMapping("/lessons")
    @ApiOperation(value = "신청한 강의 목록 조회", notes = "수강생이 신청한 강의 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getLessonListInfo(@RequestParam String email) {
        /*
        리턴 값
        강의 리스트
            - 강의명
            - 소요시간
            - 카테고리
        - 이미지
        - 별점 평균
        */
        User user = userService.getUserByAuth(email);

        if(user == null) return ResponseEntity.status(404).body(BaseResponseBody.of(404, "사용자 없음"));

        // 해당 유저가 개설한 강의 리스트
        List<Lesson> lessonList = studentService.getLessonList(user);
        if(lessonList == null) return ResponseEntity.status(404).body(BaseResponseBody.of(404, "신청 강의 없음"));

        List<LessonListGetRes> lessonListGetResList =  lessonService.setLessonProfileImgAndScore(lessonList);
        return ResponseEntity.status(200).body(lessonListGetResList);
    }
}
