package com.ssafy.myhome.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.dto.House;
import com.ssafy.myhome.model.dto.HouseDeal;
import com.ssafy.myhome.model.dto.HouseInfo;
import com.ssafy.myhome.model.repo.HouseDealDAO;

@Service
public class HouseDealServiceImpl implements HouseDealService{

	@Autowired
	HouseDealDAO repo;

	@Override
	@Transactional
	public List<House> selectHouse(String sidoName, String gugunName, String dongName) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidoName", sidoName);
		map.put("gugunName", gugunName);
		map.put("dongName", dongName);

		return repo.selectHouse(map);
	}

	
	@Override
	@Transactional
	public List<String> getSido() throws SQLException {
		return repo.getSido();
	}
	
	
	@Override
	@Transactional
	public List<String> getGu(String sido) throws SQLException {
		return repo.getGu(sido);
	}

	@Override
	@Transactional
	public List<String> getDong(String gugun, String sido) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gugun", gugun);
		map.put("sido", sido);
		return repo.getDong(map);
	}

	@Override
	@Transactional
	public List<House> selectHouseDetail(String sidoName, String gugunName, String dongName, String aptCode, String lng, String lat) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidoName", sidoName);
		map.put("gugunName", gugunName);
		map.put("dongName", dongName);
		map.put("aptCode", aptCode);
		map.put("lng", lng);
		map.put("lat", lat);
		return repo.selectHouseDetail(map);
	}

	@Override
	@Transactional
	public int avgAmount(int low, int high) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("low", low);
		map.put("high", high);
		return repo.avgAmount(map);
	}

//	@Override
//	@Transactional
//	public List<Map<String, Object>> countLocation(int amount) throws SQLException {
//		return repo.countLocation(amount);
//	}

//	@Override
//	@Transactional
//	public String selectLocation(String dongCode) throws SQLException {
//		return repo.selectLocation(dongCode);
//	}

//	@Override
//	@Transactional
//	public List<Map<Integer, Object>> avgBuild(int amount, String dongCode) throws SQLException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("amount", amount);
//		map.put("dongCode", dongCode);
//		return repo.avgBuild(map);
//	}

	@Override
	@Transactional
	public List<House> selectHouses(int dealAmount, int rentMoney, int distance, String lng, String lat) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dealAmount", dealAmount);
		map.put("rentMoney", rentMoney);
		map.put("distance", distance);
		map.put("lng", lng);
		map.put("lat", lat);
		
		return repo.selectHouses(map);
	}

//	@Override
//	@Transactional
//	public List<HouseInfo> selectInfo(String sidoName, String gugunName, String dongName) throws SQLException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sidoName", sidoName);
//		map.put("gugunName", gugunName);
//		map.put("dongName", dongName);
//		return repo.selectInfo(map);
//	}

//	@Override
//	public List<HouseDeal> selectDeal(String sidoName, String gugunName, String dongName) throws SQLException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sidoName", sidoName);
//		map.put("gugunName", gugunName);
//		map.put("dongName", dongName);
//		return repo.selectDeal(map);
//	}


	@Override
	public List<Map<String, Object>> getHealthList(String lat, String lng) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword?query=" + URLEncoder.encode("헬스클럽", "UTF-8")+"&radius=500&x=" + URLEncoder.encode(lng, "UTF-8") + "&y=" + URLEncoder.encode(lat, "UTF-8") + "&sort=distance";
		
		 HttpURLConnection conn = null;
		 StringBuffer response = new StringBuffer();
		   	 
	   	//인증키 - KakaoAK하고 한 칸 띄워주셔야해요!
		 String auth = "KakaoAK " + "735f17c46e21f44f580be47785165fde";

		 //URL 설정
		  URL url = new URL(apiUrl);
//		  System.out.println(url);
		  conn = (HttpURLConnection) url.openConnection();
		       
		  //Request 형식 설정
		  conn.setRequestMethod("GET");
	      conn.setRequestProperty("X-Requested-With", "curl");
	      conn.setRequestProperty("Authorization", auth);

	       //request에 JSON data 준비
	      conn.setDoOutput(true);
		        
		       //보내고 결과값 받기
	      int responseCode = conn.getResponseCode();
	      if (responseCode == 400) {
	           System.out.println("400:: 해당 명령을 실행할 수 없음");
	           System.out.println(conn.getResponseMessage());
	       } else if (responseCode == 401) {
	           System.out.println("401:: Authorization가 잘못됨");
	       } else if (responseCode == 500) {
	           System.out.println("500:: 서버 에러, 문의 필요");
	       } else { // 성공 후 응답 JSON 데이터받기
		       	 
	       		Charset charset = Charset.forName("UTF-8");
	       		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		            
	            String inputLine;
	            while ((inputLine = br.readLine()) != null) {
		            	response.append(inputLine); 
	            } 
	        }
	      JSONObject jObj = (JSONObject) JSONValue.parse(response.toString());
	      JSONObject meta = (JSONObject) jObj.get("meta");
//	      System.out.println(jObj.toString());
	      long size = (long) meta.get("total_count");
	      
	      if(size > 0) {
	    	  JSONArray jArray = (JSONArray) jObj.get("documents");
//	    	  System.out.println(jArray.toString());
//	    	  for(int i = 0; i < size; i++) {
//	    		  System.out.println(i);
//	    		  list.add((Map<String, Object>) jArray.get(i));
//	    		  
//	    	  }
	    	  for(Object m : jArray) {
	    		  list.add((Map<String, Object>) m);
	    	  }
	    	  
	      }
		        
		 
		 
		 return list;
	}


	@Override
	public Map<String, Object> getEventNum(String lat, String lng) throws Exception {
		int total = 0;
		int grade = 0;
		
		String[] groupCode = {"MT1", "CS2", "BK9", "CT1", "FD6", "CE7"};
		
		for(String code : groupCode) {
			String apiUrl = "https://dapi.kakao.com/v2/local/search/category?category_group_code=" + URLEncoder.encode(code, "UTF-8") +"&radius=500&x=" + URLEncoder.encode(lng, "UTF-8") + "&y=" + URLEncoder.encode(lat, "UTF-8") + "&sort=distance";
//			System.out.println(apiUrl);
			HttpURLConnection conn = null;
			StringBuffer response = new StringBuffer();
			
			//인증키 - KakaoAK하고 한 칸 띄워주셔야해요!
			String auth = "KakaoAK " + "735f17c46e21f44f580be47785165fde";
			
			//URL 설정
			URL url = new URL(apiUrl);
//		  System.out.println(url);
			conn = (HttpURLConnection) url.openConnection();
			
			//Request 형식 설정
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Requested-With", "curl");
			conn.setRequestProperty("Authorization", auth);
			
			//request에 JSON data 준비
			conn.setDoOutput(true);
			
			//보내고 결과값 받기
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				System.out.println("400:: 해당 명령을 실행할 수 없음");
				System.out.println(conn.getResponseMessage());
			} else if (responseCode == 401) {
				System.out.println("401:: Authorization가 잘못됨");
			} else if (responseCode == 500) {
				System.out.println("500:: 서버 에러, 문의 필요");
			} else { // 성공 후 응답 JSON 데이터받기
				
				Charset charset = Charset.forName("UTF-8");
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
				
				String inputLine;
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine); 
				} 
			}
			JSONObject jObj = (JSONObject) JSONValue.parse(response.toString());
			JSONObject meta = (JSONObject) jObj.get("meta");
//	      System.out.println(jObj.toString());
			long size = (long) meta.get("total_count");
			total += size;
			
		}
		
		if(total < 100) grade = 0;
		else if(total < 150) grade = 1;
		else if(total < 200) grade = 2;
		else if(total < 250) grade = 3;
		else if(total < 300) grade = 4;
		else grade = 5;
		        
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grade", grade);
		map.put("total_count", total);
		return map;
	}


	@Override
	public int avgRent(int low, int high) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("low", low);
		map.put("high", high);
		return repo.avgRent(map);
	}
	
	
}
