package com.fdmgroup.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuestionLogs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDateTime messageDate;
	private String oldQuestion;
	private String trainerName;
	
	@ManyToOne
	@JoinColumn(name = "FK_QUESTION_ID")
	private Question question;
	

	public QuestionLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionLogs(LocalDateTime messageDate, String oldQuestion, String trainerName, Question question) {
		super();
		this.messageDate = messageDate;
		this.oldQuestion = oldQuestion;
		this.trainerName = trainerName;
		this.question = question;
	}

	public QuestionLogs(int id, LocalDateTime messageDate, String oldQuestion, String trainerName, Question question) {
		super();
		this.id = id;
		this.messageDate = messageDate;
		this.oldQuestion = oldQuestion;
		this.trainerName = trainerName;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDateTime messageDate) {
		this.messageDate = messageDate;
	}

	public String getOldQuestion() {
		return oldQuestion;
	}

	public void setOldQuestion(String oldQuestion) {
		this.oldQuestion = oldQuestion;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuestionLogs [id=" + id + ", messageDate=" + messageDate + ", oldQuestion=" + oldQuestion
				+ ", trainerName=" + trainerName + ", question=" + question + "]";
	}
	

}
