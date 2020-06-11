package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Expertise {
	@Id
	@Column(name = "EX_ID")
	@SequenceGenerator(name = "EX_Seq", sequenceName = "SEQ_EX",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EX_Seq")
	private int expertiseId;
	
	@Column(name = "EX_NAME")
	private String expertiseName;


	public Expertise() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Expertise(String expertiseName) {
		super();
		this.expertiseName = expertiseName;
	}


	public String getExpertiseName() {
		return expertiseName;
	}


	public void setExpertiseName(String expertiseName) {
		this.expertiseName = expertiseName;
	}


	public int getExpertiseId() {
		return expertiseId;
	}


	@Override
	public String toString() {
		return "Expertise [expertiseId=" + expertiseId + ", expertiseName=" + expertiseName + "]";
	}

	
	
	
}
