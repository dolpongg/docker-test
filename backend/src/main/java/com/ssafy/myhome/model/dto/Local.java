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
public class Local {
	String dongCode;
	String sidoName;
	String gugunName;
	String dongName;
}
