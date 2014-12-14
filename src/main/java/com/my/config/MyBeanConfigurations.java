package com.my.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.my.controller.UserRegistrationController;
import com.my.service.UserRegistrationServiceDbImpl;
import com.my.service.UserRegistrationServiceXmlImpl;

@Configuration
@EnableAutoConfiguration
public class MyBeanConfigurations {
	@Bean
	public UserRegistrationController userRegistrationController(){
		return new UserRegistrationController();
	}

	@Bean
	public UserRegistrationServiceDbImpl userRegistrationServiceDbImpl(){
		return new UserRegistrationServiceDbImpl();
	}
	
	@Bean
	public UserRegistrationServiceXmlImpl userRegistrationServiceXmlImpl(){
		return new UserRegistrationServiceXmlImpl();
	}
	
}
