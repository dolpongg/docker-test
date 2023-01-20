package com.ssafy.myhome.model.service;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.myhome.model.dto.Local;

public interface InterestService {
	int insert(String sido, String gugun, String dong, String id) throws SQLException;
	List<Local> localList(String id) throws SQLException;
	int delete(String id, String dongCode) throws SQLException;
}
