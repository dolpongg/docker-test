package com.ssafy.myhome.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.myhome.model.repo.HouseDealDAO;
import com.ssafy.myhome.model.repo.InterestDAO;
import com.ssafy.myhome.model.repo.UserDAO;


@Configuration
@MapperScan(basePackageClasses = HouseDealDAO.class)
public class RootContextConfig {

}
