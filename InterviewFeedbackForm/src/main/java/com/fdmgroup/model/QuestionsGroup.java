package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Q_FavGroup")
public class QuestionsGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "FK_User_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "Question_id")
	private Question question;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionsGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionsGroup(User user, Question question) {
		super();
		this.user = user;
		this.question = question;
	}
	
	public QuestionsGroup(int id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
	@Override
	public String toString() {
		return "QuestionsGroup [id=" + id + ", user=" + user + ", question=" + question + "]";
	}

	
	
}
