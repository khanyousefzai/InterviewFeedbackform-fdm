package com.fdmgroup.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.QuestionLogsRepo;
import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.ReportRepo;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.QuestionLogs;
import com.fdmgroup.model.Report;
import com.fdmgroup.model.User;

/**
 * allow trainer to update question on monthly report page
 * @author Simon Duan
 *
 */
@Controller
public class MonthlyReportController {

	@Autowired
	QuestionRepo qr;
	@Autowired
	ReportRepo rr;
	@Autowired
	QuestionLogsRepo qlr;
	
	
	@RequestMapping("/approveQuestionMonthlyReport")
	public ModelAndView approveQuestion(HttpSession session, @RequestParam int questionId) {
		ModelAndView mv = new ModelAndView("trainerMonthlyReport.jsp");
		
		Question q = qr.findById(questionId);
		
		q.setApproved(1);
		qr.save(q);
		List<Report> rList =  rr.findByrStatus("new");
		session.setAttribute("report", rList.get(0));
		return mv;
	}
	
	@RequestMapping("/unapproveQuestionMonthlyReport")
	public ModelAndView unapproveQuestion(HttpSession session, @RequestParam int questionId) {
		ModelAndView mv = new ModelAndView("trainerMonthlyReport.jsp");
		
		
		Question q = qr.findById(questionId);
		
		q.setApproved(2);
		qr.save(q);
		List<Report> rList =  rr.findByrStatus("new");
		session.setAttribute("report", rList.get(0));
		return mv;
	}
	
	@RequestMapping("/updateQuestionMonthlyReport")
	public ModelAndView updateQuestion(HttpSession session, @RequestParam Integer questionId, @RequestParam String oldQuestionBody, @RequestParam String newQuestionBody) {
		ModelAndView mv = new ModelAndView("trainerMonthlyReport.jsp");
		User trainer = (User) session.getAttribute("user");
		
		//If input is empty
		if(newQuestionBody.isEmpty()) {
			System.out.println("No question body provided ");
			return mv;
		}
		
		// Find Question to update
		Optional<Question> question = qr.findById(questionId);

		if(question.isPresent())  {
			if (question.get().getQuestionBody().equals(oldQuestionBody)) {
				
				//Save old question
				QuestionLogs questionLogs = new QuestionLogs(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), oldQuestionBody, trainer.getFirstname() + " " + trainer.getLastname(), question.get());
				qlr.save(questionLogs);
				
				//Save updated question
				question.get().setQuestionBody(newQuestionBody);
				qr.save(question.get());
			}
		}
		
		//Update 'report' object
		List<Report> rList =  rr.findByrStatus("new");
		session.setAttribute("report", rList.get(0));
		return mv;
	}
	
}