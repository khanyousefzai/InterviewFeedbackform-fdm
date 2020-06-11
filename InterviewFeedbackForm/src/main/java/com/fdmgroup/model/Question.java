package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Question {
	
	
	@Id
	@Column(name = "Q_QID")
	@SequenceGenerator(name = "Q_Seq", sequenceName = "SEQ_Q",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_Seq")
	private int questionId;
	
	
	@Column(name = "Q_QBODY")
	private String questionBody;
	
	//approved 1 for approved question. 0 for not approved question. by default it is 1.
	@Column(name = "Q_APPROVED")
	private int approved;
	
	@Column(name = "NUM_LIKES")
	private int likes;
	
	@Column(name = "NUM_DISLIKES")
	private int dislikes;
	
	@OneToOne
	@JoinColumn(name = "Q_GroupId")
	private QuestionsGroup questionsgroup;
	
	@ManyToMany
	(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name= "Q_TAG") 
	private List<Tag> tagList;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "formId")
	private Form form;
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name = "LIKE_Q_USER")
	private List<User> userLiked;

	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name = "DISLIKE_Q_USER")
	private List<User> userDisliked;

	@OneToMany(mappedBy = "question")
	private List<QuestionLogs> ql = new ArrayList();
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String questionBody) {
		super();
		this.questionBody = questionBody;
		this.approved = 1;
		this.likes = 0;
		this.dislikes = 0;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	
	public int getApproved() {
		return approved;
	}
	
	
	public void setApproved(int approved) {
		this.approved = approved;
	}


	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	

	public List<User> getUserLiked() {
		return userLiked;
	}
	

	public List<User> getUserDisliked() {
		return userDisliked;
	}

	public void setUserDisliked(List<User> userDisliked) {
		this.userDisliked = userDisliked;
	}

	public void setUserLiked(List<User> userLiked) {
		this.userLiked = userLiked;
	}

	public QuestionsGroup getQuestionsgroup() {
		return questionsgroup;
	}

	public void setQuestionsgroup(QuestionsGroup questionsgroup) {
		this.questionsgroup = questionsgroup;
	}

	@Override
	public String toString() {
		List<String> tag = new ArrayList<String>();
		return "Question [questionId=" + questionId + ", questionBody=" + questionBody + "]";
	}
	
	
	
	
}
