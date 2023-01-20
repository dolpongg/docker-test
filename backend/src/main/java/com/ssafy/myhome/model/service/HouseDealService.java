package com.ssafy.myhome.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.HouseDeal;
import com.ssafy.myhome.model.dto.HouseInfo;

public interface HouseDealService {
	public List<House> selectHouse(String sidoName, String gugunName, String dongName) throws SQLException;
	
	List<String> getSido() throws SQLException;
	List<String> getGu(String sido) throws SQLException;
	List<String> getDong(String gugun, String sido) throws SQLException;

	List<House> selectHouseDetail(String sidoName, String gugunName, String dongName,
			String aptCode, String lng, String lat) throws SQLException;
	
	int avgAmount(int low, int high) throws SQLException;
	
//	List<Map<String, Object>> countLocation(int amount) throws SQLException;
//	String selectLocation(String dongCode) throws SQLException;
	
//	List<Map<Integer, Object>> avgBuild(int amount, String dongCode) throws SQLException;
	List<House> selectHouses(int dealAmount, int rentMoney, int distance, String lng, String lat) throws SQLException;
	
//	List<HouseInfo> selectInfo(String sidoName, String gugunName, String dongName) throws SQLException;

//	List<HouseDeal> selectDeal(String sidoName, String gugunName, String dongName)  throws SQLException;
	List<Map<String, Object>> getHealthList(String lat, String lng) throws Exception;
	Map<String, Object> getEventNum(String lat, String lng) throws Exception;

	public int avgRent(int parseInt, int parseInt2);
}

