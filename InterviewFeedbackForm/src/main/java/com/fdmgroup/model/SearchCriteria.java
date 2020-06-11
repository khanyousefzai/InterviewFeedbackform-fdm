package com.fdmgroup.model;

public class SearchCriteria {
	
	private String clientName;
	private String clientDivision;
	private String tagName;
	private String questionBody;
	private String roleName;
	private String sortResult;
	public SearchCriteria(String clientName, String clientDivision, String tagName, String questionBody,
			String roleName, String sortResult) {
		super();
		this.clientName = clientName;
		this.clientDivision = clientDivision;
		this.tagName = tagName;
		this.questionBody = questionBody;
		this.roleName = roleName;
		this.sortResult = sortResult;
	}
	public String getClientName() {
		return clientName;
	}
	public String getClientDivision() {
		return clientDivision;
	}
	public String getTagName() {
		return tagName;
	}
	public String getQuestionBody() {
		return questionBody;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getSortResult() {
		return sortResult;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setClientDivision(String clientDivision) {
		this.clientDivision = clientDivision;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setSortResult(String sortResult) {
		this.sortResult = sortResult;
	}

	
}
