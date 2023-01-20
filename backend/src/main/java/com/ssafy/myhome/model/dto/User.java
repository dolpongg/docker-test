package com.ssafy.myhome.model.dto;
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
public class User {
	private String id;
	private String pass;
	private String name;
	private String email;
	private String joindate;
	private String type;
	private String address;
	private String lat;
	private String lng;
}
