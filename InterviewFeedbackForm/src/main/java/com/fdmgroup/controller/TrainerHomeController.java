package com.fdmgroup.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fdmgroup.dao.ClientRepo;
import com.fdmgroup.dao.FormRepo;
import com.fdmgroup.dao.QuestionLogsRepo;
import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.TagRepo;
import com.fdmgroup.dao.TrainerRepo;
import com.fdmgroup.model.Trainer;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fdmgroup.dao.ClientRepo;
import com.fdmgroup.dao.FormRepo;
import com.fdmgroup.dao.QuestionLogsRepo;
import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.TagRepo;
import com.fdmgroup.dao.TrainerRepo;
import com.fdmgroup.model.Client;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.QuestionLogs;
import com.fdmgroup.model.SearchCriteria;
import com.fdmgroup.model.SearchResult;
import com.fdmgroup.model.Tag;
import com.fdmgroup.model.User;

/**
 * Used to build pages for the trainer, conduct other functionality related to
 * trainer
 * 
 * @author Ibtisam C, Daniel P, Simon D
 *
 */

@Controller
public class TrainerHomeController {

	@Autowired
	TrainerRepo trainerRepo;

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	TagRepo tagRepo;

	@Autowired
	ClientRepo clientRepo;

	@Autowired
	FormRepo formRepo;

	@Autowired
	QuestionLogsRepo questionLogsRepo;

	@RequestMapping("/trainerHome")
	public ModelAndView trainerHome() {
		ModelAndView mv = new ModelAndView("trainerHome.jsp");
		mv.addObject("question", new Question());
		return mv;
	}

	@RequestMapping("/monthlyReport")
	public ModelAndView monthlyReport() {
		ModelAndView mv = new ModelAndView("trainerMonthlyReport.jsp");
		return mv;
	}
	

	/**
	 * Update questions based on input provided by the trainer on their search
	 * 
	 * @param newQBody The new question body
	 * @param oldQBody The old question body
	 * @param session  Current session
	 * @return A fresh search ModelAndView is returned with an appropriate status
	 *         message stating is the update was successful or not
	 */
	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
	public ModelAndView updateQuestion(@RequestParam(required = false, name = "questionBodyUpdate") String newQBody,
			@RequestParam(required = false, name = "oldQuestionBody") String oldQBody, @RequestParam(required = false, name = "qid") Integer qid, HttpSession session) {
		ModelAndView mv = new ModelAndView("trainerQuestion.jsp");
		mv.addObject("visiableControl", "hidden");
		mv.addObject("disableControl", "submit");
		
		User trainer = (User) session.getAttribute("user");
		
		if (newQBody.isEmpty()) {
			mv.addObject("errMsg", "No Update Question Body Provided!");
			return mv;
		}

		// Find Question to update
		Optional<Question> question = questionRepo.findById(qid);

		if(question.isPresent())  {
			if (question.get().getQuestionBody().equals(oldQBody)) {
				
				//Save old question
				QuestionLogs questionLogs = new QuestionLogs(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), oldQBody, trainer.getFirstname() + " " + trainer.getLastname(), question.get());
				questionLogsRepo.save(questionLogs);
				
				//Save updated question
				question.get().setQuestionBody(newQBody);
				questionRepo.save(question.get());
			}
		}

		mv.addObject("succMsg", "Question Updated Successfully!");		
		return mv;
	}

	/**
	 * Searches for questions based on criteria provided by the Trainer
	 * 
	 * @param cList      The name of the client
	 * @param tList      The name of the Tag
	 * @param qList      The part of question body to match
	 * @param rList      The Role name
	 * @param sortResult The type of sorting the user wants
	 * @param session    The current HTTPSession object
	 * @return A ModelAndView object with the questions found that match the
	 *         Trainer's criteria
	 */
	@RequestMapping(value = "/searchQuestions", method = RequestMethod.POST)
	public ModelAndView searchQuestions(SearchCriteria searchCriteria) {

		SearchResult searchResult = search(searchCriteria.getClientName(), searchCriteria.getClientDivision(), searchCriteria.getTagName(), searchCriteria.getQuestionBody(), searchCriteria.getRoleName(), searchCriteria.getSortResult());

		ModelAndView mv = new ModelAndView("trainerQuestion.jsp");	
		mv.addObject("resultQuestions", searchResult.getResultQuestions());
		mv.addObject("resultClients", searchResult.getResultClients());
		mv.addObject("resultTags", searchResult.getResultTags());
		mv.addObject("resultRoles", searchResult.getResultRoles());
		mv.addObject("resultDivisions", searchResult.getResultDivisions());
		mv.addObject("resultsQuestionLogs", searchResult.getqLogs());
		mv.addObject("searchCriteria", searchCriteria);
		mv.addObject("visiableControl", "submit");
		mv.addObject("disableControl", "hidden");
		
		
		return mv;
	}
	//Extract search method from searchQuestions()
	public SearchResult search(String cList, String dList, String tList, String qList, String rList, String sortResult) {
		SearchResult searchResult = new SearchResult();
		
		List<Question> myList = new ArrayList<>();		
		// Sort by
		if(sortResult.equals("default")) {
			myList = questionRepo.findAllByOrderByQuestionId();
		}
		if (sortResult.equals("likes")) {
			myList = questionRepo.findAllByOrderByLikesDesc();
		}
		if (sortResult.equals("dislikes")) {
			myList = questionRepo.findAllByOrderByDislikesDesc();
		}
		if (sortResult.equals("alphabetical")) {
			myList = questionRepo.findAllByOrderByQuestionBody();
		}
		// Match Questions with client and tag
				List<Question> resultQuestions = searchResult.getResultQuestions();
				List<String> resultClients = searchResult.getResultClients();
				List<List<Tag>> resultTags = searchResult.getResultTags();
				List<String> resultRoles = searchResult.getResultRoles();
				List<String> resultDivisions = searchResult.getResultDivisions();
				List<Iterable<QuestionLogs>> qLogs = searchResult.getqLogs();
				
				for (Question q : myList) {
					if (q.getTagList().toString().contains("tagName=" + tList)
							&& q.getForm().getClient().getClientName().equals(cList)
							&& q.getQuestionBody().toLowerCase().contains(qList.toLowerCase())
							&& q.getForm().getRole().contains(rList)
							&& q.getForm().getClientDivision().contains(dList)) {
						resultQuestions.add(q);
						String clientName = q.getForm().getClient().getClientName();
						resultClients.add(clientName);
						List<Tag> questionTags = q.getTagList();
						resultTags.add(questionTags);
						resultRoles.add(q.getForm().getRole());
						resultDivisions.add(q.getForm().getClientDivision());
						
						qLogs.add(questionLogsRepo.findAllByQuestionOrderByMessageDateAsc(q));
						
					} else if (cList.isEmpty() && q.getTagList().toString().contains("tagName=" + tList)
							&& q.getQuestionBody().toLowerCase().contains(qList.toLowerCase())
							&& q.getForm().getRole().contains(rList)
							&& q.getForm().getClientDivision().contains(dList)) {
						resultQuestions.add(q);
						String clientName = q.getForm().getClient().getClientName();
						resultClients.add(clientName);
						List<Tag> questionTags = q.getTagList();
						resultTags.add(questionTags);
						resultRoles.add(q.getForm().getRole());
						resultDivisions.add(q.getForm().getClientDivision());
						
						qLogs.add(questionLogsRepo.findAllByQuestionOrderByMessageDateAsc(q));
						
					}
				}
		
		return searchResult;
		
	}
	
	@RequestMapping(value = "/downloadSearchResult", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadSearchResult(SearchCriteria searchCriteria) throws IOException{
		
		//do another search() with saved search criteria
		SearchResult searchResult = search(searchCriteria.getClientName(), searchCriteria.getClientDivision(), searchCriteria.getTagName(), searchCriteria.getQuestionBody(), searchCriteria.getRoleName(), searchCriteria.getSortResult());
		//Generate pdf		
		ByteArrayInputStream bis = SearchResultPDFGenerator.searchResultPDFReport(searchResult,  searchCriteria);
		
		HttpHeaders headers = new HttpHeaders();
		
        headers.add("Content-Disposition", "inline; filename=SearchResult.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
		
	}
}
