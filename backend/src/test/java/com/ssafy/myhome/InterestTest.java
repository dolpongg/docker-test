//package com.ssafy.myhome;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ssafy.myhome.model.dto.Local;
//import com.ssafy.myhome.model.service.InterestService;
//
//@SpringBootTest
//@Transactional
//public class InterestTest {
//	@Autowired
//	InterestService service;
//
//	@Test
//	void insertTest() throws SQLException {
////		selectHouseDetail(Connection con, String sidoName, String gugunName, String dongName, int dealYear, int dealMonth, String APTName)
//		//give
//		String sidoName = "서울특별시";
//		String gugunName = "종로구";
//		String dongName = "숭인동";
//		String id = "ssafy";
//
//		//when
//		int result = service.insert(sidoName, gugunName, dongName, id);
//		//then
//		assertEquals(result, 1);
//	}
//
//	@Test
//	void localListTest() throws SQLException {
////		selectHouseDetail(Connection con, String sidoName, String gugunName, String dongName, int dealYear, int dealMonth, String APTName)
//		//give
//		String id = "ssafy";
//
//		//when
//		List<Local> result = service.localList(id);
//		//then
//		assertEquals(result.size(), 0);
//	}
//
//
//	@Test
//	void deleteTest() throws SQLException {
//		//give
//		String dongCode = "1111017500";
//		String id = "ssafy";
//		//when
//		List<Local> result = service.localList(id);
//		//then
//		assertEquals(result.size(), 0);
//	}
//
//
//
//}
