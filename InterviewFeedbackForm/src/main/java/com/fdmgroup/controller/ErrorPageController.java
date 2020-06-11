package com.fdmgroup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController {
	@RequestMapping("/backToHome")
	public ModelAndView backHome(HttpSession session) {
		
		if(session.getAttribute("type") != null) {
			String t = (String)session.getAttribute("type");
			if(t.equals("trainee")) {
				ModelAndView home = new ModelAndView("home.jsp");
				return home;
			}else if(t.equals("admin")){
				ModelAndView home = new ModelAndView("adminHome.jsp");
				return home;
			}else if(t.equals("accountManager")){
				ModelAndView home = new ModelAndView("accountManagerHome.jsp");
				return home;
			}else{
				ModelAndView home = new ModelAndView("trainerHome.jsp");
				return home;
			}
			
		}else {
			ModelAndView home = new ModelAndView("index.jsp");
			return home;
		}

		
	}
}
