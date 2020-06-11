package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.ClientRepo;
import com.fdmgroup.dao.FormRepo;
import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.TagRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.Client;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.Tag;
import com.fdmgroup.model.User;

/**
 * Used to build pages for the trainee, conduct search related to trainee
 * 
 * @author Ibtisam C, Daniel P, Simon D
 *
 */
@Controller
public class TraineeController {

	@Autowired
	FormRepo formRepo;

	@Autowired
	ClientRepo clientRepo;

	@Autowired
	TagRepo tagRepo;

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	UserRepo userRepo;

	
	/**
	 * Returns the correct formhub page for the user based on their account type
	 * 
	 * @param session The current session that has the relevant attributes
	 * @return Appropriate home page is displayed to user
	 */
	@RequestMapping("/formhub")
	public ModelAndView Home(HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser.getPermission() == 1) {
			ModelAndView formPage = new ModelAndView("home.jsp");
			return formPage;
		} else if (currentUser.getPermission() == 2) {
			ModelAndView formPage = new ModelAndView("trainerHome.jsp");
			formPage.addObject("question", new Question());
			return formPage;
		} else if (currentUser.getPermission() == 3) {
			ModelAndView formPage = new ModelAndView("accountManagerHome.jsp");
			formPage.addObject("question", new Question());
			return formPage;
		}

		return null;
	}

	/**
	 * Used to show either the trainee, account manager, or trainer their relevant
	 * question hub page
	 * 
	 * @param session
	 * @return Relevant Questionhub.jsp Page
	 */
	@RequestMapping("/questionhub")
	public ModelAndView questionHub(HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser.getPermission() == 1 || currentUser.getPermission() == 3) {
			ModelAndView formPage = new ModelAndView("traineeQuestion.jsp");
			return formPage;
		} else if (currentUser.getPermission() == 2) {
			ModelAndView formPage = new ModelAndView("trainerQuestion.jsp");
			formPage.addObject("visiableControl", "hidden");
			formPage.addObject("disableControl", "submit");
			formPage.addObject("question", new Question());
			return formPage;
		}
		return null;
	}

	/**
	 * Uses this to show the trainee
	 * 
	 * @param model
	 * @param currentForm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewForm", method = RequestMethod.GET)
	public ModelAndView viewMyPersistenObject(ModelMap model, @RequestParam String currentForm, HttpSession session) {
		model.addAttribute("currentForm", formRepo.findById(Integer.parseInt(currentForm)));
		List<Question> question = formRepo.findById(Integer.parseInt(currentForm)).getQuestionList();
		model.addAttribute("questionList", question);
		User curr = (User) session.getAttribute("user");
		User current = userRepo.findById(curr.getUserId()).get();
		ModelAndView index = new ModelAndView("viewForm.jsp");
		model.addAttribute("user", current);
		List<Integer> check = new ArrayList<Integer>();
		for (int i = 0; i < question.size(); i++) {
			if (question.get(i).getUserLiked().contains(current) || question.get(i).getUserLiked() == null) {
				check.add(1);
			} else if (question.get(i).getUserDisliked().contains(current)
					|| question.get(i).getUserDisliked() == null) {
				check.add(2);
			} else {
				check.add(0);
			}
		}
		model.addAttribute("check", check);
		return index;
	};

	/**
	 * Searches for questions based on criteria provided by the trainee
	 * 
	 * @param cList      The name of the client
	 * @param tList      The name of the Tag
	 * @param qList      The part of question body to match
	 * @param rList      The Role name
	 * @param sortResult The type of sorting the user wants
	 * @return A ModelAndView object with the questions found that match the
	 *         Trainee's criteria
	 */
	@RequestMapping(value = "/searchQuestionsTrainee", method = RequestMethod.POST)
	public ModelAndView searchQuestionsTrainee(@RequestParam(required = false, name = "clientName") String cList,
			@RequestParam(required = false, name = "clientDivision") String dList,
			@RequestParam(required = false, name = "tagName") String tList,
			@RequestParam(required = false, name = "questionBody") String qList,
			@RequestParam(required = false, name = "roleName") String rList,
			@RequestParam(required = false, name = "sortResult") String sortResult) {
		List<Question> queryResult = new ArrayList<Question>();
		// Get all clients with the provided client name
		List<Client> client = clientRepo.findByClientNameIgnoreCase(cList);
		if (client.size() > 0) {
			List<Form> clientForms = client.get(0).getFormList();
			for (Form f : clientForms) {
				List<Question> formQuestions = f.getQuestionList();
				if (formQuestions.size() > 0) {
					queryResult.addAll(formQuestions);
				}
			}
		}
		
		//Find all client divisions with provided client division
				List<Form> forms = formRepo.findByClientDivision(dList);
				for (Form form : forms) {
					queryResult.addAll(form.getQuestionList()); 
				}
				
		// Find all tags with the provided tag
		List<Tag> tag = tagRepo.findByTagNameIgnoreCase(tList);
		if (tag.size() == 1) {
			queryResult.addAll(tag.get(0).getQuestionList());
		}
		// Find all questiosn that contain the body provided
		Iterable<Question> allQuestions = questionRepo.findAll();
		for (Question q : allQuestions) {
			if (q.getQuestionBody() != null) {
				if (q.getQuestionBody().toLowerCase().contains(qList.toLowerCase()) && !qList.equals("")) {
					queryResult.add(q);
				}
			}
		}
		// Find all forms and get their question lists for the roles
		Iterable<Form> allForms = formRepo.findAll();
		for (Form f : allForms) {
			if (f.getRole().equalsIgnoreCase(rList)) {
				queryResult.addAll(f.getQuestionList());
			}
		}
		// Remove Duplicates
		List<Question> uniqueQuestionList = new ArrayList<Question>();
		for (Question q : queryResult) {
			if (!uniqueQuestionList.contains(q)) {
				uniqueQuestionList.add(q);
			}
		}
		
		List<Question> myList = new ArrayList<>();
		
		//Sort by
		if(sortResult.equals("default")) {
			myList = questionRepo.findAllByOrderByQuestionId();
		}
		if(sortResult.equals("likes")) {
			myList = questionRepo.findAllByOrderByLikesDesc();
		}
		
		if(sortResult.equals("dislikes")) {
			myList = questionRepo.findAllByOrderByDislikesDesc();
		}
		
		if(sortResult.equals("alphabetical")) {
			myList = questionRepo.findAllByOrderByQuestionBody();
		}
		
		
		//Match Questions with client and tag
		List<String> resultQuestions = new ArrayList<String>();
		List<String> resultClients = new ArrayList<String>();
		List<List<Tag>> resultTags = new ArrayList<List<Tag>>();
		List<String> resultRoles = new ArrayList<String>();
		List<String> resultDivisions = new ArrayList<String>();
		for (Question q : myList) {
			if (q.getTagList().toString().contains("tagName=" + tList) && q.getForm().getClient().getClientName().equals(cList)
					&& q.getQuestionBody().toLowerCase().contains(qList.toLowerCase())
					&& q.getForm().getRole().contains(rList) && q.getForm().getClientDivision().toLowerCase().contains(dList.toLowerCase())) {
				if (q.getApproved() == 1) {
					resultQuestions.add(q.getQuestionBody());
					String clientName = q.getForm().getClient().getClientName();
					resultClients.add(clientName);
					List<Tag> questionTags = q.getTagList();
					resultTags.add(questionTags);
					resultRoles.add(q.getForm().getRole());
					resultDivisions.add(q.getForm().getClientDivision());
				}
			} else if (cList.isEmpty() && q.getTagList().toString().contains("tagName=" + tList)
					&& q.getQuestionBody().toLowerCase().contains(qList.toLowerCase())
					&& q.getForm().getRole().contains(rList) && q.getForm().getClientDivision().toLowerCase().contains(dList.toLowerCase())) {
				if (q.getApproved() == 1) {
					resultQuestions.add(q.getQuestionBody());
					String clientName = q.getForm().getClient().getClientName();
					resultClients.add(clientName);
					List<Tag> questionTags = q.getTagList();
					resultTags.add(questionTags);
					resultRoles.add(q.getForm().getRole());
					resultDivisions.add(q.getForm().getClientDivision());
				}
			}
		}

		ModelAndView mv = new ModelAndView("traineeQuestion.jsp");
		mv.addObject("resultQuestions", resultQuestions);
		mv.addObject("resultClients", resultClients);
		mv.addObject("resultTags", resultTags);
		mv.addObject("resultRoles", resultRoles);
		mv.addObject("resultDivisions",resultDivisions);	
		return mv;
	}

	/**
	 * Signs out the trainee
	 * 
	 * @param session Current HTTPSession, used to invalidate the user's session
	 * @return Home Page of the application
	 */
	@RequestMapping(value = "/signout")
	public ModelAndView signout(HttpSession session) {
		session.invalidate();
		ModelAndView index = new ModelAndView("index.jsp");
		return index;
	};

}
