package com.fdmgroup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FDM_ADMIN")
@DiscriminatorValue(value = "ADMIN")
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, String email, String firstname, String lastname, int permission) {
		super(username, password, email, firstname, lastname, permission);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [" + super.toString() + "]";
	}

	
}
