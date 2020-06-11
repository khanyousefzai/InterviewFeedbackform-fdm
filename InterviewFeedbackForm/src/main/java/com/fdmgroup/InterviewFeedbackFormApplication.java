package com.fdmgroup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

import org.apache.log4j.Logger;


@SpringBootApplication
public class InterviewFeedbackFormApplication implements ApplicationRunner {
	
	private static Logger logger = AppLogger.getInstance().getUserLogger();

	public static void main(String[] args) {
		SpringApplication.run(InterviewFeedbackFormApplication.class, args);
	}

	 @Override
	    public void run(ApplicationArguments applicationArguments) throws Exception {
		 
			logger.info("test");
	      
	    }
	 
	 @Configuration
	 public class ApplicationConfig {

	     @Bean
	     public Filter securityFilter() {
	         return new SecurityFilter();
	     }

	 }
}
