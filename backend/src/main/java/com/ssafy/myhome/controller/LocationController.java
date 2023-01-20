package com.ssafy.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/location")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin("*")
public class LocationController {
	
	public static final Logger logger = LoggerFactory.getLogger(memberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	HouseDealService HouseService;

	@ApiOperation(value = "시도 리스트 가져오기", notes = "DB에 있는 시도 목록을 가져 온다.", response = Map.class)
	@GetMapping()
	public ResponseEntity<?> getSidoList() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<String> result = HouseService.getSido();
			resultMap.put("sidoList", result);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("시도 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@ApiOperation(value = "구군 리스트 가져오기", notes = "DB에 있는 구군 목록을 가져 온다.", response = Map.class)
	@GetMapping("/{sido}")
	public ResponseEntity<?> getGugunList(@PathVariable("sido") @ApiParam(value = "조회할 시도", required = true) String sido) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<String> result = HouseService.getGu(sido);
			resultMap.put("gugunList", result);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("시도 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	
	@ApiOperation(value = "동 리스트 가져오기", notes = "DB에 있는 동 목록을 가져 온다.", response = Map.class)
	@GetMapping("/{sido}/{gugun}")
	public ResponseEntity<?> getDongList(@PathVariable("sido") @ApiParam(value = "조회할 시", required = true) String sido, @PathVariable("gugun") @ApiParam(value = "조회할 구군", required = true) String gugun) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<String> result = HouseService.getDong(gugun, sido);
			resultMap.put("dongList", result);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("시도 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
}
