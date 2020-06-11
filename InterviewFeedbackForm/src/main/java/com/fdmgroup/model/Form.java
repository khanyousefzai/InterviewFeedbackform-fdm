package com.fdmgroup.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Form {

	@Id
	@Column(name = "F_ID")
	@SequenceGenerator(name = "F_Seq", sequenceName = "SEQ_F", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_Seq")
	private int formId;

	@Column(name = "F_CREATEDATE")
	private Date interviewDate;

	@Column(name = "F_FORMCREATED")
	private Date formCreated;

	@Column(name = "F_CLIENTMANAGER")
	private String clientManager;

	@Column(name = "F_INTERVIEWERNAME")
	private String interviewerName;

	@Column(name = "F_ROLE")
	private String role;

	@Column(name = "F_LOCATION")
	private String location;

	@Column(name = "F_FDMACCOUNTMANAGER")
	private String fdmAccountManager;

	@Column(name = "F_INTERVIEWMETHOD")
	private String interviewMethod;

	@Column(name = "F_FDMSTREAM")
	private String fdmStream;

	@Column(name = "F_ACADEMYLOCATION")
	private String academyLocation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PK_traineeId")
	private Trainee author;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clientId")
	private Client client;

	@OneToMany(mappedBy = "form")
	private List<Question> questionList;

	
	private String clientDivision;

	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Form(Date createDate, String fdmStream, String academyLocation, Trainee author, Client client,
			List<Question> questionList) {
		super();
		this.interviewDate = createDate;
		this.fdmStream = fdmStream;
		this.academyLocation = academyLocation;
		this.author = author;
		this.client = client;
		this.questionList = questionList;

	}

	public Form(Date interviewDate, String fdmStream, String academyLocation, Trainee author, Client client,
			List<Question> questionList, String clientDivision) {
		super();
		this.interviewDate = interviewDate;
		this.fdmStream = fdmStream;
		this.academyLocation = academyLocation;
		this.author = author;
		this.client = client;
		this.questionList = questionList;
		this.clientDivision = clientDivision;
	}

	

	public String getClientDivision() {
		return clientDivision;
	}

	public void setClientDivision(String clientDivision) {
		this.clientDivision = clientDivision;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getClientManager() {
		return clientManager;
	}

	public void setClientManager(String clientManager) {
		this.clientManager = clientManager;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFdmAccountManager() {
		return fdmAccountManager;
	}

	public void setFdmAccountManager(String fdmAccountManager) {
		this.fdmAccountManager = fdmAccountManager;
	}

	public String getInterviewMethod() {
		return interviewMethod;
	}

	public void setInterviewMethod(String interviewMethod) {
		this.interviewMethod = interviewMethod;
	}

	public String getFdmStream() {
		return fdmStream;
	}

	public void setFdmStream(String fdmStream) {
		this.fdmStream = fdmStream;
	}

	public String getAcademyLocation() {
		return academyLocation;
	}

	public void setAcademyLocation(String academyLocation) {
		this.academyLocation = academyLocation;
	}

	public Trainee getAuthor() {
		return author;
	}

	public void setAuthor(Trainee author) {
		this.author = author;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public int getFormId() {
		return formId;
	}

	public Date getFormCreated() {
		return formCreated;
	}

	public void setFormCreated(Date formCreated) {
		this.formCreated = formCreated;
	}

	@Override
	public String toString() {
		return "Form [createDate=" + interviewDate + ", client=" + client + ", questionList=" + questionList + "]";
	}

}
