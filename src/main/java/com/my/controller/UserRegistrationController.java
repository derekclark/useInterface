package com.my.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.my.service.UserRegistrationService;

public class UserRegistrationController {
	@Resource(name="userRegistrationServiceXmlImpl")
	UserRegistrationService userRegistrationService;
	
    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

	public void processRequest(){
		userRegistrationService.save();
	}
}
