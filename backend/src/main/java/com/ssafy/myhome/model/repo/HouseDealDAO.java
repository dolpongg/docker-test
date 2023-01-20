package com.ssafy.myhome.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.HouseDeal;
import com.ssafy.myhome.model.dto.HouseInfo;

@Repository
public interface HouseDealDAO {
	List<String> getSido() throws SQLException;
	List<String> getGu(String sido) throws SQLException;
	List<String> getDong(Map<String, Object> map) throws SQLException;
	
//	List<HouseInfo> selectInfo(Map<String, Object> map) throws SQLException;
//	List<HouseDeal> selectDeal(Map<String, Object> map) throws SQLException;
	
	List<House> selectHouse(Map<String, Object> map) throws SQLException;
	List<House> selectHouseDetail(Map<String, Object> map) throws SQLException;
	
	int avgAmount(Map<String, Object> map) throws SQLException;
	

//	String selectLocation(String dongCode) throws SQLException;
	
//	List<Map<Integer, Object>> avgBuild(Map<String, Object> map) throws SQLException;
	List<House> selectHouses(Map<String, Object> map) throws SQLException;
	int avgRent(Map<String, Object> map);
}

