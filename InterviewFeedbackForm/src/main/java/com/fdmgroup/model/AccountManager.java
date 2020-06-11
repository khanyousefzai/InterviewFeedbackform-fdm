package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "ACCOUNTMANAGER")
public class AccountManager extends User {

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ACCOUNTMANAGER_CLIENT", joinColumns = {
				@JoinColumn(name = "FK_ACCOUNTMANAGER")}, inverseJoinColumns = {
				@JoinColumn(name = "FK_CLIENT")})
	private List<Client> clientList;
	
	
	
	public AccountManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountManager(String username, String password, String email, String firstname, String lastname,
			int permission) {
		super(username, password, email, firstname, lastname, permission);
		this.clientList = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}


	@Override
	public String toString() {
		return "AccountManager [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getEmail()="
				+ getEmail() + ", getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname()
				+ ", getPermission()=" + getPermission() + ", getUserId()=" + getUserId() + "]";
	}
	
}
