package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

public class Tag {
	@Id
	@Column(name = "T_TAGID")
	@SequenceGenerator(name = "T_Seq", sequenceName = "SEQ_T",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_Seq")
	private int tagId;
	
	@Column(name = "TAG_TAGNAME")
	private String tagName;
	
	@ManyToMany(mappedBy = "tagList",fetch = FetchType.EAGER)
	private List<Question> questionList;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tagName) {
		super();
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public int getTagId() {
		return tagId;
	}

	@Override
	public String toString() {
		List<String> question = new ArrayList<String>();
		questionList.forEach((x) -> question.add(x.getQuestionId()+" " + x.getQuestionBody()));
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", questionList=" + question + "]";
	}
	
	
	
	
}
