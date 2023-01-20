package com.ssafy.myhome.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.dto.User;
import com.ssafy.myhome.model.service.JwtServiceImpl;
import com.ssafy.myhome.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin("*")
public class memberController {

	public static final Logger logger = LoggerFactory.getLogger(memberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = memberService.login(user);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
				memberService.saveRefreshToken(user.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = memberService.userInfo(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, user : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(user.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원 가입", notes = "회원 정보를 등록한다", response = Map.class)
	@PostMapping
	protected ResponseEntity<Map<String, Object>> registerUser(@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보(아이디, 비밀번호, 이름, 이메일, 회원유형, 주소).", required = true) User user){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String[] XY = com.ssafy.myhome.util.location.getXY(user.getAddress());
			user.setLat(XY[0]);
			user.setLng(XY[1]);
			int result = memberService.insert(user);
			if(result == -1) {
				resultMap.put("message", FAIL);
				status = HttpStatus.CONFLICT;
			}else {
				resultMap.put("message",SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			logger.error("회원 정보 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다", response = Map.class)
	@PutMapping
	protected ResponseEntity<Map<String, Object>> editInfo(@RequestBody @ApiParam(value = "회원 정보 수정 시 필요한 회원정보(아이디-수정x, 비밀번호, 이름, 이메일, 회원유형, 주소).", required = true) User user, HttpServletRequest request) throws ServletException, IOException, SQLException {
//		session.setAttribute("user", new User(user.getId(),pass,name,email));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				String[] XY = com.ssafy.myhome.util.location.getXY(user.getAddress());
				user.setLat(XY[0]);
				user.setLng(XY[1]);
				memberService.update(user);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("회원 정보 수정 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원 탈퇴", notes = "회원 정보를 삭제한다", response = Map.class)
	@DeleteMapping("/{userid}")
	protected ResponseEntity<Map<String, Object>> dropUser(@PathVariable("userid") String userid, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				memberService.delete(userid);
				memberService.deleRefreshToken(userid);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}catch (Exception e) {
				logger.error("회원 정보 수정 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
    @ApiOperation(value = "아이디 중복 체크", notes = "아이디 존재를 반환한다.", response = Map.class)
    	@GetMapping("/check/{userid}")
    	public ResponseEntity<Map<String, Object>> checkId(
    			@PathVariable("userid") @ApiParam(value = "확인할 아이디.", required = true) String userid,
    			HttpServletRequest request) {
    //		logger.debug("userid : {} ", userid);
    		Map<String, Object> resultMap = new HashMap<>();
    		HttpStatus status = HttpStatus.UNAUTHORIZED;
    		try {
    //			로그인 사용자 정보.
    			logger.info("ID조회!!! {}", userid);
    			User user = memberService.userInfo(userid);
    			if(user == null) {
    				logger.info("success");
    				resultMap.put("message", SUCCESS);
    			}
    			else {
    				logger.info("no");
    				resultMap.put("message", "no");
    			}
    			status = HttpStatus.ACCEPTED;
    		} catch (Exception e) {
    			logger.error("정보조회 실패 : {}", e);
    			resultMap.put("message", e.getMessage());
    			status = HttpStatus.INTERNAL_SERVER_ERROR;
    		}
    		return new ResponseEntity<Map<String, Object>>(resultMap, status);
    	}


}
