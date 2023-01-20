package com.ssafy.myhome.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.dto.User;
import com.ssafy.myhome.model.repo.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO repo;

	@Override
	@Transactional
	public int insert(User user) throws SQLException {
		return repo.insert(user);
	}
//
//	@Override
//	@Transactional
//	public User select(String id, String pass) throws SQLException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", id);
//		map.put("pass", pass);
//		return repo.select(map);
//	}
//
	@Override
	@Transactional
	public int update(User user) throws SQLException {
		return repo.update(user);
	}

	@Override
	@Transactional
	public int delete(String id) throws SQLException {
		return repo.delete(id);
	}

	@Override
	public User login(User memberDto) throws Exception {
		if (memberDto.getId() == null || memberDto.getPass() == null)
			return null;
		return repo.login(memberDto);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return repo.userInfo(userid);
	}

	@Override
	@Transactional
	public int saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", refreshToken);
		System.out.println(map);
		return repo.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return repo.getRefreshToken(userid);
	}

	@Override
	@Transactional
	public int deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", null);
		return repo.deleteRefreshToken(map);
	}

}
