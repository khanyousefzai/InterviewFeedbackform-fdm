package com.fdmgroup.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "TRAINEE")
public class Trainee extends User{

	@Column(name = "STREAM")
	private String stream;
	
	@OneToMany(mappedBy = "author")
	private List<Form> formList;
	
	
	public Trainee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trainee(String username, String password, String email, String firstname, String lastname, int permission, String stream) {
		super(username, password, email, firstname, lastname, permission);
		this.stream = stream;
		// TODO Auto-generated constructor stub
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
	
	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> form) {
		this.formList = form;
	}

	@Override
	public String toString() {
		return "Trainee [stream=" + stream + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getFirstname()="
				+ getFirstname() + ", getLastname()=" + getLastname() + ", getPermission()=" + getPermission()
				+ ", getUserId()=" + getUserId() + "]";
	}

		
	
	
	
}
