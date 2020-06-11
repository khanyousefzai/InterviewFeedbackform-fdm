package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
	
	private List<Question> resultQuestions = new ArrayList<Question>();
	private List<String> resultClients = new ArrayList<String>();
	private List<List<Tag>> resultTags = new ArrayList<List<Tag>>();
	private List<String> resultRoles = new ArrayList<String>();
	private List<String> resultDivisions = new ArrayList<String>();
	private List<Iterable<QuestionLogs>> qLogs = new ArrayList<Iterable<QuestionLogs>>();
	
	
	public SearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Question> getResultQuestions() {
		return resultQuestions;
	}


	public List<String> getResultClients() {
		return resultClients;
	}


	public List<List<Tag>> getResultTags() {
		return resultTags;
	}


	public List<String> getResultRoles() {
		return resultRoles;
	}


	public List<String> getResultDivisions() {
		return resultDivisions;
	}


	public List<Iterable<QuestionLogs>> getqLogs() {
		return qLogs;
	}



	
}
