package com.fdmgroup;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class AppLogger {
	private static AppLogger appLogger = null;
	private Logger systemLogger = null;
	private Logger userLogger = null;
	
	private AppLogger() {
		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		systemLogger = Logger.getLogger("systemLogger");
		userLogger = Logger.getLogger("userLogger");
	}
	
	public static AppLogger getInstance() {
		if (appLogger == null) {
			appLogger = new AppLogger();
		}
		return appLogger;
	}
	
	public Logger getSystemLogger() {
		return systemLogger;
	}
	
	public Logger getUserLogger() {
		return userLogger;
	}
	
	
	
}
