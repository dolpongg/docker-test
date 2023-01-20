package com.ssafy.myhome.model.service;
import java.sql.SQLException;

import com.ssafy.myhome.model.dto.User;

public interface UserService {
//	int insert(User user) throws SQLException;
//	User select(String id, String pass) throws SQLException;
//	int update(User user) throws SQLException;
//	int delete(String id) throws SQLException;
	
	public User login(User User) throws Exception;
	public User userInfo(String userid) throws Exception;
	public int saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public int deleRefreshToken(String userid) throws Exception;
	int update(User user) throws SQLException;
	public int insert(User user) throws SQLException;
	public int delete(String userid) throws SQLException;
}
