package com.fdmgroup.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "TRAINER")

public class Trainer extends User {
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TRAINER_EXPERTISE", joinColumns = {
				@JoinColumn(name = "FK_TRAINEER")}, inverseJoinColumns = {
				@JoinColumn(name = "FK_EXPERTISE")})
	List<Expertise> expertiseList;

	public Trainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trainer(String username, String password, String email, String firstname, String lastname, int permission) {
		super(username, password, email, firstname, lastname, permission);
		// TODO Auto-generated constructor stub
	}

	public List<Expertise> getExpertiseList() {
		return expertiseList;
	}

	public void setExpertiseList(List<Expertise> expertiseList) {
		this.expertiseList = expertiseList;
	}

	@Override
	public String toString() {
		return "Trainer [expertiseList=" + expertiseList + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getEmail()=" + getEmail() + ", getFirstname()=" + getFirstname()
				+ ", getLastname()=" + getLastname() + ", getPermission()=" + getPermission() + ", getUserId()="
				+ getUserId() + "]";
	}
	
	
	
	
}
