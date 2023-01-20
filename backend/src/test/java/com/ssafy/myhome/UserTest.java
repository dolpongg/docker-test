//package com.ssafy.myhome;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ssafy.myhome.model.dto.User;
//import com.ssafy.myhome.model.service.UserService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Transactional
//public class UserTest {
//
//	@Autowired
//	UserService service;
//
//	@Test
//	void insertTest() throws SQLException {
//		//give
//		User user = User.builder().email("ssafy1@ssafy.com").id("ssafy1").name("이싸피").pass("1234").build();
//		//when
//		int result = service.insert(user);
//		//then
//		assertEquals(result, 1);
//	}
//
////	@Test
////	void selectTest() throws SQLException {
////		//give
////		String id = "ssafy";
////		String pass = "1234";
////		//when
////		User result = service.select(id, pass);
////		//then
////		assertNotNull(result);
////	}
//
//	@Test
//	void updateTest() throws SQLException {
//		//give
//		User user = User.builder().email("ssafy1@ssafy.com").id("ssafy").name("이싸피").pass("1234").build();
//		//when
//		int result = service.update(user);
//		//then
//		assertEquals(result, 1);
//	}
//
//	@Test
//	void deleteTest() throws SQLException {
//		//give
//		String id = "ssafy";
//		//when
//		int result = service.delete(id);
//		//then
//		assertEquals(result, 1);
//	}
//
////	public User login(User User) throws Exception;
//	@Test
//	void loginTest() throws Exception {
//		//give
//		User user = User.builder().email("ssafy1@ssafy.com").id("ssafy").name("이싸피").pass("1234").build();
//		//when
//		User result = service.login(user);
//		//then
//		System.out.println(result.toString());
////		log.debug("result : " + result.toString());
//		assertNotNull(result);
//	}
//
//	@Test
//	void userInfoTest() throws Exception {
//		//give
//		String id = "ssafy";
//		//when
//		User result = service.userInfo(id);
//		//then
//		System.out.println(result.toString());
////		log.debug("result : " + result.toString());
//		assertNotNull(result);
//	}
//
//	@Test
//	void saveRefreshTokenTest() throws Exception {
//		//give
//		String id = "ssafy";
//		String refreshToken = "1ewfe1";
//
//		//when
//		int result = service.saveRefreshToken(id, refreshToken);
//
//		//then
//		assertEquals(result, 1);
//	}
//
//	@Test
//	void deleteRefreshTokenTest() throws Exception {
//		//give
//		String id = "ssafy";
//
//		//when
//		int result = service.deleRefreshToken(id);
//
//		//then
//		assertEquals(result, 1);
//	}
//
//	@Test
//	void localTest() throws Exception{
//		String address = "서울 성동구 서울숲길 17";
//
//		String[] result = com.ssafy.myhome.util.location.getXY(address);
//
//		System.out.println(Arrays.toString(result));
//		assertNotNull(result);
//	}
//
//}
