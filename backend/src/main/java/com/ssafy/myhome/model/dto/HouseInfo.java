package com.ssafy.myhome.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseInfo {
	private int APTCode;
	
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String lng;
	private String lat;
	private int img;

}
