package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class SecurityQuestion {
	@Id
	@Column(name = "SQ_ID")
	@SequenceGenerator(name = "SQ_Seq", sequenceName = "SEQ_SQ",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_Seq")
	private int securityQuestionId;
	
	@Column(name = "securityQUESTION")
	private String securityQuestion;

	public SecurityQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SecurityQuestion(String securityQuestion) {
		super();
		this.securityQuestion = securityQuestion;
	}

	public int getsecurityQuestionId() {
		return securityQuestionId;
	}

	public void setsecurityQuestionId(int securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	@Override
	public String toString() {
		return "SecurityQuestion [securityQuestionId=" + securityQuestionId + ", securityQuestion=" + securityQuestion + "]";
	}
	
	
	
}
