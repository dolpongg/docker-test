package com.ssafy.myhome.model.repo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.myhome.model.dto.Local;

@Repository
public interface InterestDAO {
	String select(Map<String, Object> map) throws SQLException;
	int insert(Map<String, Object> map) throws SQLException;
	List<Local> localList(String id) throws SQLException;
	int delete(Map<String, Object> map) throws SQLException;
}
