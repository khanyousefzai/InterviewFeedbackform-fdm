package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.ReportRepo;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.Report;

/**
 * Scheduled task for generating monthly report
 * @author Simon
 *
 */
@Configuration
@EnableScheduling
public class ReportController {
	@Autowired
	QuestionRepo qr;
	
	@Autowired
	ReportRepo rr;
	
	//@Scheduled(cron = "0 0 8 1 * ?")
	@Scheduled(fixedDelay = 3600000)
	public void generateMonthlyRepor(){
		List<Question> qList = new ArrayList<Question>();
		Iterable<Question> qI = qr.findAllByOrderByDislikesDesc();

		  int length = 0; 
		  for(Question q : qI)length++; length = (int) (length*0.3);
		  for(Question q : qI) { 
			  if(length!=0) { 
				  qList.add(q); 
				  length--; 
			  }else { 
				  break;
			  } 
		  }
	
		Report newReport = new Report("new",qList);
		java.util.Date d1 = new java.util.Date();
		
		newReport.setReportCreated(new java.sql.Date(d1.getTime()));
		
		Iterable<Report> foundReport = rr.findAll();
		foundReport.forEach((x) -> {
			if(x.getrStatus().equals("new")) {
				x.setrStatus("old");
				rr.save(x);
			}
			
		});		
		
		rr.save(newReport);
	}
}
