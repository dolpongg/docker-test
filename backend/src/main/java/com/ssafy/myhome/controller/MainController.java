package com.ssafy.myhome.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.Local;
import com.ssafy.myhome.model.dto.User;
import com.ssafy.myhome.model.service.HouseDealService;
import com.ssafy.myhome.model.service.HouseDealServiceImpl;
import com.ssafy.myhome.model.service.InterestServiceImpl;
import com.ssafy.myhome.model.service.JwtServiceImpl;
import com.ssafy.myhome.model.service.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/main")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin("*")
public class MainController extends HttpServlet {
	
	public static final Logger logger = LoggerFactory.getLogger(memberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final long serialVersionUID = 1L;

	@Autowired
	HouseDealServiceImpl HouseService;
	
	@Autowired
	InterestServiceImpl interestService;
	
	@Autowired
	UserServiceImpl userService;

	@Autowired
	private JwtServiceImpl jwtService;
	
	
	
	@ApiOperation(value = "관심 지역 추가", notes = "관심 지역으로 추가한다.", response = Map.class)
	@PostMapping("/interest")
	protected ResponseEntity<Map<String, Object>> interestAdd(HttpServletRequest request, @RequestBody String sido, @RequestBody String gugun, @RequestBody String dong, @RequestBody String userid) throws ServletException, IOException, SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				logger.info("사용 가능한 토큰!!!");
				int result = interestService.insert(sido, gugun, dong, userid);
				if(result==-1) {
					resultMap.put("message", FAIL);
				}else {
					resultMap.put("message", SUCCESS);
				}
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	@ApiOperation(value = "매물 디테일 정보 가져오기", notes = "매물의 디테일 정보를 반환한다.", response = Map.class)
	@GetMapping("/{sido}/{gugun}/{dong}/{aptCode}/{userid}")
	public ResponseEntity<Map<String, Object>> getDetail(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun, @PathVariable("dong") String dong, @PathVariable("aptCode") String aptCode, @PathVariable("userid") String userid){

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			User user = userService.userInfo(userid);
			
			List<House> houses = HouseService.selectHouseDetail(sido, gugun, dong, aptCode, user.getLng(), user.getLat());
			if(houses.size()>0) {
				for(House house:houses) {
					house.setEvent(HouseService.getEventNum(house.getLat(), house.getLng()));
					house.setHealth(HouseService.getHealthList(house.getLat(), house.getLng()));
					
				}
			}
			resultMap.put("houses", houses);
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@ApiOperation(value = "매물 목록 가져오기", notes = "매물 목록을 반환한다.", response = Map.class)
	@GetMapping("/{sido}/{gugun}/{dong}")
	public ResponseEntity<Map<String, Object>> getlist(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun, @PathVariable("dong") String dong){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			List<House> houses = HouseService.selectHouse(sido, gugun, dong);
			
			if(houses.size()>0) {
				for(House house:houses) {
					house.setSidoName(sido);
					house.setGugunName(gugun);
				}
			}
			resultMap.put("houses", houses);
		
		}catch (Exception e) {
			logger.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	

}
