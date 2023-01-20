package com.ssafy.myhome.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.dto.Local;
import com.ssafy.myhome.model.repo.InterestDAO;

@Service
public class InterestServiceImpl implements InterestService{
	@Autowired
	InterestDAO repo;

	@Override
	@Transactional
	public int insert(String sido, String gugun, String dong, String id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		map.put("dong", dong);
		String dongCode = repo.select(map);
		System.out.println(dongCode);
		
		map = new HashMap<String, Object>();
		map.put("dongCode", dongCode);
		map.put("id", id);
		int result = repo.insert(map);
		return result;
	}

	@Override
	@Transactional
	public List<Local> localList(String id) throws SQLException {
		return repo.localList(id);
	}

	@Override
	public int delete(String id, String dongCode) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dongCode", dongCode);
		map.put("id", id);
		return repo.delete(map);
	}
	
	
	
}
