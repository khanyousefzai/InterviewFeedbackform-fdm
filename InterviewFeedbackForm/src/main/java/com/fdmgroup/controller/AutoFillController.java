package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * Used mainly as a utility class to store methods related to autofill
 * @author Ibtisam C
 *
 */
@Controller
public class AutoFillController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	FormRepo formRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	TagRepo tagRepo;

	@Autowired
	ClientRepo clientRepo;
	
	
	/**
	 * Display the roles that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the roles in the database with what the user has typed
	 */
	@RequestMapping(value = "/getRoleList", method=RequestMethod.GET)
	public @ResponseBody List<String> getRoleList(@RequestParam("term") String query){
		List<Form> allForms = formRepo.findAll();
		List<String> allRoles = new ArrayList<String>();
		for(Form f : allForms) {
			//unique role names
			if(!allRoles.contains(f.getRole())) {
				allRoles.add(f.getRole());
			}
		}
		return allRoles;
	}
	
	/**
	 * Display the role locations that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the role locations in the database with what the user has typed
	 */
	@RequestMapping(value = "/getRoleLocations", method = RequestMethod.GET)
	public @ResponseBody List<String> getRoleLocations(@RequestParam("term") String query){
		List<Form> allForms = formRepo.findAll();
		List<String> allRoleLoc = new ArrayList<String>();
		for(Form f : allForms) {
			//unique check
			if(!allRoleLoc.contains(f.getLocation())) {
				allRoleLoc.add(f.getLocation());
			}
		}
		return allRoleLoc;
	}
	
	/**
	 * Display the questions that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the questions in database with what the user has typed
	 */
	@RequestMapping(value="/getQuestionList", method=RequestMethod.GET)
	public @ResponseBody List<String> getQuestionList(@RequestParam("term") String qbody){
		Iterable<Question> allQuestions = questionRepo.findAll();
		List<String> relativeQuestionBody = new ArrayList<String>();
		for(Question q : allQuestions) {
			if(q.getQuestionBody() != null) {
				if(q.getQuestionBody().toLowerCase().contains(qbody.toLowerCase())) {
					relativeQuestionBody.add(q.getQuestionBody());
				}
			}
		}
		return relativeQuestionBody;
	}
	/**
	 * Display the Tags that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the Tags in database with what the user has typed
	 */
	@RequestMapping(value="/getTagList", method=RequestMethod.GET)
	public @ResponseBody List<String> getTagList(@RequestParam("term") String query){
		Iterable<Tag> allTags = tagRepo.findAll();
		List<String> relativeTagList = new ArrayList<String>();
		for(Tag t : allTags) {
			if(t.getTagName().toLowerCase().contains(query.toLowerCase())) {
				relativeTagList.add(t.getTagName());
			}
		}
		return relativeTagList;
	}
	/**
	 * Display the Tags that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the Clients in database with what the user has typed
	 */
	@RequestMapping(value="/getClientList", method=RequestMethod.GET)
	public @ResponseBody List<String> getClientList(@RequestParam("term") String query){
		Iterable<Client> allClients = clientRepo.findAll();
		List<String> relativeClientList = new ArrayList<String>();
		for(Client c : allClients) {
			if(c.getClientName().toLowerCase().contains(query.toLowerCase())) {
				relativeClientList.add(c.getClientName());
			}
		}
		return relativeClientList;
	}
	/**
	 * Display the Tags that match the user's typed string
	 * @param query The string typed by the user so far
	 * @return List that matches the Usernames in database with what the user has typed
	 */
	@RequestMapping(value="/getUsernameList", method=RequestMethod.GET)
	public @ResponseBody List<String> getAllUsernameList(@RequestParam("term") String query){
		Iterable<User> allUsers = userRepo.findAll();
		List<String> allFoundUsernames = new ArrayList<String>();
		for(User u : allUsers) {
			if(u.getUsername().toLowerCase().contains(query.toLowerCase())) {
				allFoundUsernames.add(u.getUsername());
			}
		}
		return allFoundUsernames;
	}
	
	
	
}
