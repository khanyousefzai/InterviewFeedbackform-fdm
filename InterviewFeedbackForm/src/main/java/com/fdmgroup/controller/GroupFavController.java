package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.QuestionsGroupRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.QuestionsGroup;
import com.fdmgroup.model.User;

@Controller
public class GroupFavController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	QuestionsGroupRepo questiongroupRepo;
	
	/* This method is allowing the user to create its own user's favorite questions list
	 *	Once the user press add button then it will add it to the favorite list
	 * after search query user can see the button to add it to the list
	 */
	@RequestMapping(method = RequestMethod.POST, value="/groupfav")
	public String groupfav(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Optional<User> managedUser = userRepo.findById(user.getUserId());
		int found_question_id;
		int current_user_id;
		int question_group_user_id;
		int question_group_question_id;
		current_user_id = managedUser.get().getUserId();	
		QuestionsGroup questionsgroup = new QuestionsGroup();		
		String question = request.getParameter("questionBody");
	//	System.out.println(request.getParameter("questionBody"));
		Question temp = (Question) questionRepo.findByQuestionBody(question);
		found_question_id = questionRepo.findByQuestionBody(question).getQuestionId();
	//	System.out.println(found_question_id);
		current_user_id = managedUser.get().getUserId();
		System.out.println(user);
		System.out.println(temp);
		QuestionsGroup temporary = (QuestionsGroup) questiongroupRepo.findByUserAndQuestion(user, temp);
		if(temporary == null) {
			questionsgroup.setQuestion(temp);
			questionsgroup.setUser(managedUser.get());
			QuestionsGroup savedQuestionGroup = questiongroupRepo.save(questionsgroup);
	    //	System.out.println("asdasd" + savedQuestionGroup);
			return "traineeQuestion.jsp";
		}
		else {
		//	System.out.println(temporary);
			return "traineeQuestion.jsp";
			}
		}
	

	@RequestMapping(method = RequestMethod.GET, value="/viewFavourites")
	public String viewFavourites(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<QuestionsGroup> qg = questiongroupRepo.findByUser(user);
		List <Question> questions = new ArrayList();	
		for (QuestionsGroup questionsGroup : qg) {
			questions.add(questionsGroup.getQuestion());
//			System.out.println(questionsGroup.toString());
		}
		request.setAttribute("Questions", questions);
		return "viewquestion.jsp";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/groupunfav")
	public ModelAndView groupunfav(@RequestParam int questionId, HttpSession session) {
		ModelAndView mvc = new ModelAndView("viewquestion.jsp");
		User user = (User) session.getAttribute("user");
	//	Optional<User> managedUser1 = userRepo.findById(user.getUserId());	
		Question question = questionRepo.findById(questionId);
		
		System.out.println(question);
		System.out.println(user);
	//	System.out.println(managedUser1.get().getUserId());
		
		QuestionsGroup temporary = (QuestionsGroup) questiongroupRepo.findByUserAndQuestion(user, question);
		System.out.println(temporary);
		questiongroupRepo.delete(temporary);
		List<QuestionsGroup> qg = questiongroupRepo.findByUser(user);
		List <Question> questions = new ArrayList();	
		for (QuestionsGroup questionsGroup : qg) {
			questions.add(questionsGroup.getQuestion());
//			System.out.println(questionsGroup.toString());
		}
		mvc.addObject("Questions", questions);
		
		return mvc;
	}	
	
	@RequestMapping(method = RequestMethod.POST, value="/back")
	public String back(HttpServletRequest request, HttpSession session) {
		return "home.jsp";
	}
}



