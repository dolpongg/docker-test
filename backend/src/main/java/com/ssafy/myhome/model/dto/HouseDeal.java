package com.ssafy.myhome.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseDeal {

	private int APTCode;

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
	
}
