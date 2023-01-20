package com.ssafy.myhome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.HouseDeal;
import com.ssafy.myhome.model.dto.HouseInfo;
import com.ssafy.myhome.model.service.HouseDealService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class MyhomeFrameworkApplicationTests {

	@Autowired
	HouseDealService service;
//	
//	@Test
//	void selectGuTest() throws SQLException {
//		//when
//		List<String> result = service.selectGu();
//		//then
//		assertEquals(result.size(), 25);
//	}
//	
//	
//	@Test
//	void selectDongTest1() throws SQLException {
//		//give
//		String gugun="종로구";
//		
//		//when
//		List<String> result = service.selectDong(gugun);
//		//then
//		assertEquals(result.size(), 87);
//	}
//	
//	
//
//	@Test
//	void selectDongTest() throws SQLException {
//		//give
//		String gugun = "종로구";
//		//when
//		List<String> result = service.selectDong(gugun);
//		//then
//		assertEquals(result.size(), 87);
//	}
//	
//	@Test
//	void selectInfoTest() throws SQLException {
//		//give
//		String sidoName = "서울특별시";
//		String gugunName = "종로구";
//		String dongName = "숭인동";
//		
//		//when
//		List<HouseInfo> result = service.selectInfo(sidoName, gugunName, dongName);
//		//then
//		assertEquals(result.size(), 17);
//	}
//	
//	@Test
//	void selectDealTest() throws SQLException {
//		//give
//		String sidoName = "서울특별시";
//		String gugunName = "종로구";
//		String dongName = "숭인동";
//		
//		//when
//		List<HouseDeal> result = service.selectDeal(sidoName, gugunName, dongName);
//		//then
//		assertEquals(result.size(), 108);
//	}
//	
	@Test
	void selectHouseTest() throws SQLException {
		//give
		String sidoName = "서울특별시";
		String gugunName = "관악구";
		String dongName = "봉천동";
		
		//when
		List<House> result = service.selectHouse(sidoName, gugunName, dongName);
		System.out.println(result);
		//then
		assertEquals(result.size(), 3);
	}

	

	@Test
	void selectHouseDetailTest() throws SQLException {
//		selectHouseDetail(Connection con, String sidoName, String gugunName, String dongName, int dealYear, int dealMonth, String APTName)
		//give
		String sidoName = "서울특별시";
		String gugunName = "관악구";
		String dongName = "봉천동";
		String aptCode = "34225951";
		
		//when
		List<House> result = service.selectHouseDetail(sidoName, gugunName, dongName, aptCode, "126.93367448383607","37.48318340153049");
		System.out.println(result);
		//then
		assertEquals(result.size(), 1);
	}
//	
//
//	
//	@Test
//	void countLocationTest() throws SQLException {
////		selectHouseDetail(Connection con, String sidoName, String gugunName, String dongName, int dealYear, int dealMonth, String APTName)
//		//give
//		int low = 100000;
//		int high = 150000;
//		
//		//when
//		List<Map<String, Integer>> result = service.countLocation(122000);
//		//then
//		assertEquals(result.size(), 97);
//	}
//	
//	
//	
//	@Test
//	public void selectLocationTest() throws SQLException {
//		String result = service.selectLocation("1111011500");
//		log.debug("result: {}",result);
//		assertNotNull(result);
//	}
//	
//	@Test
//	public void avgBuildTest() throws SQLException {
//		List<Map<Integer, Integer>> result = service.avgBuild(80000, "1111017500");
//		log.debug("result: {}",result);
//		assertNotNull(result);
//	}
//	
//	@Test
//	public void selectHousesTest() throws SQLException {
//		List<House> result = service.selectHouses(1000, "1111017500", 2012);
//		log.debug("result: {}",result);
//		assertNotNull(result);
//	}
	
	
	@Test
	public void getHealthListTest() throws Exception{
		List<Map<String, Object>> result = service.getHealthList("37.47997734698158", "126.94467953851219");
		log.debug("result : {}", result);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void getEventTest() throws Exception{
		Map<String, Object> result = service.getEventNum("37.5482464117151", "127.039635833604");
		log.debug("result : {}", result);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void getRentAvg() throws Exception{
		int result = service.avgRent(2, 65);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void getFinal() throws Exception{
		List<House> result = service.selectHouses(7000, 50, 1000, "126.94467953851219", "37.47997734698158");
		System.out.println(result);
		assertNotNull(result);
	}
}
