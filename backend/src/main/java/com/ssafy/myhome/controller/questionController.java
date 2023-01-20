package com.ssafy.myhome.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.User;
import com.ssafy.myhome.model.service.HouseDealServiceImpl;
import com.ssafy.myhome.model.service.JwtServiceImpl;
import com.ssafy.myhome.model.service.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/question")
@Api("매물추천 api")
@CrossOrigin("*")
public class questionController extends HttpServlet {
	
	public static final Logger logger = LoggerFactory.getLogger(memberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final long serialVersionUID = 1L;
    
	@Autowired
	HouseDealServiceImpl HouseService;
	
	@Autowired
	JwtServiceImpl jwtService;
	
	@Autowired
	UserServiceImpl memberService;
	
	
	@ApiOperation(value = "매물 추천 - 최종", notes = "조건들에 맞는 최종 매물 리스트를 반환합니다.", response = Map.class)
	@GetMapping("/final/{dealAmount}/{rentMoney}/{distance}/{userid}")
	private ResponseEntity<Map<String, Object>> getFinal(HttpServletRequest request, @PathVariable String dealAmount, @PathVariable String rentMoney, @PathVariable String distance, @PathVariable String userid) throws SQLException, ServletException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				User user = memberService.userInfo(userid); 
				System.out.println(userid);
				System.out.println(user.getLng());
				System.out.println(user.getLat());
				List<House> result = HouseService.selectHouses(Integer.parseInt(dealAmount), Integer.parseInt(rentMoney), Integer.parseInt(distance), user.getLng(), user.getLat());
				if(result == null) {
				resultMap.put("message", FAIL);
			}else {
				resultMap.put("houses", result);
				resultMap.put("message", SUCCESS);
			}
				
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		};
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	

	@ApiOperation(value = "매물 추천 - 거리", notes = "거리 조건 탐색", response = Map.class)
	@GetMapping("/location/{distance}")
	private ResponseEntity<Map<String, Object>> rent(HttpServletRequest request,  @PathVariable String distance) throws SQLException, ServletException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				if(Integer.parseInt(distance) == 0) {
					resultMap.put("distance", Integer.toString(Integer.parseInt(distance) + 500));
				}else {
					resultMap.put("distance", Integer.toString(Integer.parseInt(distance) + 500));
				}
				
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
			}
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		};
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	
	@ApiOperation(value = "매물 추천 - 월세", notes = "월세 조건 탐색", response = Map.class)
	@GetMapping("/rent/{rentMoney}/{low}/{high}/{flag}")
	private ResponseEntity<Map<String, Object>> rent(HttpServletRequest request,  @PathVariable String rentMoney,  @PathVariable String low,   @PathVariable String high,  @PathVariable String flag) throws SQLException, ServletException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				logger.info("사용 가능한 토큰!!!");
				if(Integer.parseInt(rentMoney) == 0) {
					low = "0";
					high = Integer.toString(Integer.MAX_VALUE);
				}else {
					if(flag.equals("down")) {
						high = rentMoney;
					}else {
						low = rentMoney;
					}
				}
				int result = HouseService.avgRent(Integer.parseInt(low), Integer.parseInt(high));
				
				resultMap.put("rentMoney", Integer.toString(result));
				resultMap.put("low", low);
				resultMap.put("high", high);
				
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
			}
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		};
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	
	
	@ApiOperation(value = "매물 추천 - 보증금", notes = "관심 지역으로 추가한다.", response = Map.class)
	@GetMapping("/deal/{dealAmount}/{low}/{high}/{flag}")
	private ResponseEntity<Map<String, Object>> amount(HttpServletRequest request,  @PathVariable String dealAmount,  @PathVariable String low,  @PathVariable String high, @PathVariable String flag) throws SQLException, ServletException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				logger.info("사용 가능한 토큰!!!");
				if(Integer.parseInt(dealAmount) == 0) {
					low = "0";
					high = Integer.toString(Integer.MAX_VALUE);
				}else {
					if(flag.equals("down")) {
						high = dealAmount;
					}else {
						low = dealAmount;
					}
				}
				int result = HouseService.avgAmount(Integer.parseInt(low), Integer.parseInt(high));
				
				resultMap.put("dealAmount", Integer.toString(result));
				resultMap.put("low", low);
				resultMap.put("high", high);
				
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
			}
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		};
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}



}
