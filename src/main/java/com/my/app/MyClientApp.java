package com.my.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.my.config.MyBeanConfigurations;
import com.my.controller.UserRegistrationController;

@Import (MyBeanConfigurations.class)

public class MyClientApp  implements CommandLineRunner{
	@Autowired UserRegistrationController userRegistrationController;
	
	public static void main(String[] args){
		ApplicationContext ctx = SpringApplication.run(MyClientApp.class, args);
	}
	
	public void run(String[] args){
		System.out.println("here");
		userRegistrationController.processRequest();
	}
	
}
