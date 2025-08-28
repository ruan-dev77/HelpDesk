package com._dev_ruan.helpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com._dev_ruan.helpDesk.services.DBService;
@Configuration
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	
			

}
