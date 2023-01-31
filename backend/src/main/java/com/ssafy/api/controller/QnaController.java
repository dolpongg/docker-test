package com.ssafy.api.controller;

import com.ssafy.api.request.QnaRegisterPostReq;
import com.ssafy.api.request.QnaUpdatePutReq;
import com.ssafy.api.response.NoticeInfoRes;
import com.ssafy.api.response.PageGetRes;
import com.ssafy.api.response.QnaInfoGetRes;
import com.ssafy.api.response.QnaListGetRes;
import com.ssafy.api.service.QnaService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Notice;
import com.ssafy.db.entity.qna.Qna;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "QnA API", tags = {"QnA"})
@RestController
@RequestMapping("/api/v1/qna")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @PostMapping()
    @ApiOperation(value = "Qna 등록", notes = "제목, 내용, 시간과 작성자 이메일 입력 후 Qna등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<? extends BaseResponseBody> registQna(@RequestBody QnaRegisterPostReq qnaRegisterPostReq){

        qnaService.createQna(qnaRegisterPostReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
    }

    @DeleteMapping()
    @ApiOperation(value = "Qna 삭제", notes = "Qna 아이디와 로그인한 사람의 이메일을 받아, 비교한 뒤 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "권한이 없음")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteQna(@RequestParam String email, @RequestParam Long id) throws Exception {

        try{
            qnaService.deleteQna(email, id);
        } catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,"권한이 없음"));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
    }

    @GetMapping()
    @ApiOperation(value = "qna 상세 조회", notes = "qna를 클릭했을 때, qna id를 입력받아 공지 상세정보 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> getQnaInfo(@RequestParam Long id){

        Qna qna = qnaService.readQna(id);
        QnaInfoGetRes qnaInfoGetRes = new QnaInfoGetRes(qna);

        return ResponseEntity.status(200).body(qnaInfoGetRes);
    }

    @GetMapping("/list")
    @ApiOperation(value = "qna 리스트 조회", notes = "내가 한 qna 리스트 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> getQnaList(@RequestParam int offset, @RequestParam int limit, @RequestParam String email){

        List<Qna> qnaList = qnaService.readQnaList(offset, limit, email);
        List<QnaListGetRes> qnaListGetResList = qnaList
                .stream()
                .map(q -> new QnaListGetRes(q)).collect(Collectors.toList());
        Long qnaCount = qnaService.qnaCount();

        PageGetRes qnaPage = new PageGetRes();
        qnaPage.setCount(qnaCount);
        qnaPage.setPage(qnaListGetResList);

        return ResponseEntity.status(200).body(qnaPage);

    }

    @PutMapping()
    @ApiOperation(value = "qna 수정", notes = "내가 한 qna 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "권한이 없음")
    })
    public ResponseEntity<? extends BaseResponseBody> updateQna(@RequestBody QnaUpdatePutReq qnaUpdatePutReq) {

        try{
            qnaService.updateQna(qnaUpdatePutReq);
        } catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,"권한이 없음"));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));

    }



}
