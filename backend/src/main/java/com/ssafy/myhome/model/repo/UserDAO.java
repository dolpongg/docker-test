package com.ssafy.myhome.model.repo;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.myhome.model.dto.User;

@Repository
public interface UserDAO {
//	int insert(User user) throws SQLException;
//	User select(Map<String, Object> map) throws SQLException;
//	int update(User user) throws SQLException;
//	int delete(String id) throws SQLException;
	
	public User login(User user) throws SQLException;
	public User userInfo(String userid) throws SQLException;
	public int saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public int deleteRefreshToken(Map<String, String> map) throws SQLException;
	public int update(User user);
	public int insert(User user);
	public int delete(String id);
}
