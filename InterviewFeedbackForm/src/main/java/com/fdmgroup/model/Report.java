package com.fdmgroup.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MonthlyReport")
public class Report {
	@Id
	@Column(name = "R_REPORTID")
	@SequenceGenerator(name = "R_Seq", sequenceName = "SEQ_R",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_Seq")
	private int reportId;
	
	
	//status of the report could be new or old, there will be only one new report existing in database.
	@Column(name = "R_STATUS",unique = false)
	private String rStatus;
	
	@Column(name = "R_CREATED",unique = false)
	private Date reportCreated;
	
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="REPORT_QUESTION", 
    		joinColumns={@JoinColumn(name="REPORTID")}, 
    		inverseJoinColumns={@JoinColumn(name="QUESTIONID")})
	private List<Question> questionList;


	public Report(String rStatus, List<Question> questionList) {
		super();
		this.rStatus = rStatus;
		this.questionList = questionList;
	}


	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getrStatus() {
		return rStatus;
	}


	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}


	public Date getReportCreated() {
		return reportCreated;
	}


	public void setReportCreated(Date reportCreated) {
		this.reportCreated = reportCreated;
	}


	public List<Question> getQuestionList() {
		return questionList;
	}


	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}


	public int getReportId() {
		return reportId;
	}


	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", rStatus=" + rStatus + ", reportCreated=" + reportCreated + "]";
	}
	
	
	
	
}
