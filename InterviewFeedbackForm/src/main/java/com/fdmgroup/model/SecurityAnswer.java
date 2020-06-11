package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityAnswer {
	
	@Id
	@Column(name = "U_ID")
	private int userId;
	
	//@ManyToOne(cascade= CascadeType.ALL)
	@Column(name = "SQ1_ID")
	private int firstSecurityQuestionId;
	
	@Column(name = "SECURITYANSWER1")
	private String firstSecurityAnswer;
	
	@Column(name = "SQ2_ID")
	private int secondSecurityQuestionId;
	
	@Column(name = "SECURITYANSWER2")
	private String secondSecurityAnswer;

	@Column(name = "SQ3_ID")
	private int thirdSecurityQuestionId;
	
	@Column(name = "SECURITYANSWER3")
	private String thirdSecurityAnswer;

	public SecurityAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public SecurityAnswer(int userId, int firstSecurityQuestionId, String firstSecurityAnswer,
			int secondSecurityQuestionId, String secondSecurityAnswer, int thirdSecurityQuestionId,
			String thirdSecurityAnswer) {
		super();
		this.userId = userId;
		this.firstSecurityQuestionId = firstSecurityQuestionId;
		this.firstSecurityAnswer = firstSecurityAnswer;
		this.secondSecurityQuestionId = secondSecurityQuestionId;
		this.secondSecurityAnswer = secondSecurityAnswer;
		this.thirdSecurityQuestionId = thirdSecurityQuestionId;
		this.thirdSecurityAnswer = thirdSecurityAnswer;
	}

	public SecurityAnswer(int firstSecurityQuestionId, String firstSecurityAnswer, int secondSecurityQuestionId,
			String secondSecurityAnswer, int thirdSecurityQuestionId, String thirdSecurityAnswer) {
		super();
		this.firstSecurityQuestionId = firstSecurityQuestionId;
		this.firstSecurityAnswer = firstSecurityAnswer;
		this.secondSecurityQuestionId = secondSecurityQuestionId;
		this.secondSecurityAnswer = secondSecurityAnswer;
		this.thirdSecurityQuestionId = thirdSecurityQuestionId;
		this.thirdSecurityAnswer = thirdSecurityAnswer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFirstSecurityQuestionId() {
		return firstSecurityQuestionId;
	}
	
	public void setFirstSecurityQuestionId(int firstSecurityQuestionId) {
		this.firstSecurityQuestionId = firstSecurityQuestionId;
	}

	public String getFirstSecurityAnswer() {
		return firstSecurityAnswer;
	}

	public void setFirstSecurityAnswer(String firstSecurityAnswer) {
		this.firstSecurityAnswer = firstSecurityAnswer;
	}

	public int getSecondSecurityQuestionId() {
		return secondSecurityQuestionId;
	}

	public void setSecondSecurityQuestionId(int secondSecurityQuestionId) {
		this.secondSecurityQuestionId = secondSecurityQuestionId;
	}

	public String getSecondSecurityAnswer() {
		return secondSecurityAnswer;
	}

	public void setSecondSecurityAnswer(String secondSecurityAnswer) {
		this.secondSecurityAnswer = secondSecurityAnswer;
	}

	public int getThirdSecurityQuestionId() {
		return thirdSecurityQuestionId;
	}

	public void setThirdSecurityQuestionId(int thirdSecurityQuestionId) {
		this.thirdSecurityQuestionId = thirdSecurityQuestionId;
	}

	public String getThirdSecurityAnswer() {
		return thirdSecurityAnswer;
	}

	public void setThirdSecurityAnswer(String thirdSecurityAnswer) {
		this.thirdSecurityAnswer = thirdSecurityAnswer;
	}

	@Override
	public String toString() {
		return "SecurityAnswer [userId=" + userId + ", firstSecurityQuestionId=" + firstSecurityQuestionId
				+ ", firstSecurityAnswer=" + firstSecurityAnswer + ", secondSecurityQuestionId="
				+ secondSecurityQuestionId + ", secondSecurityAnswer=" + secondSecurityAnswer
				+ ", thirdSecurityQuestionId=" + thirdSecurityQuestionId + ", thirdSecurityAnswer="
				+ thirdSecurityAnswer + "]";
	}
	
	




}
