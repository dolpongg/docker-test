package com.ssafy.myhome.model.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class House {

	private int APTCode;
	
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String lng;
	private String lat;
	private int img;
	
	private int dealAmount;
	private String area;
	private String floor;
	private int rentMoney;
	private String loan;
	private String type;
	private String title;
	private String description;
	private String updated;
	private String url;
	private String nearSubways;
	
	private Map<String,Object> event;
	private List<Map<String,Object>> health;
	
	private Object distance;

}
