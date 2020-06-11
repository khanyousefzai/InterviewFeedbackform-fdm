package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Client {
	@Id
	@Column(name = "C_ID")
	@SequenceGenerator(name = "C_Seq", sequenceName = "SEQ_C", initialValue = 32, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "C_Seq")
	private int clientId;

	@Column(name = "C_CLIENTNAME")
	private String clientName;

	@OneToMany(mappedBy = "client")
	private List<Form> formList;

	@ManyToMany(mappedBy = "clientList")
	private List<AccountManager> accountManagerList;

	

	
	public Client(String clientName) {
		super();
		this.clientName = clientName;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<Form> getFormList() {
		return formList;
	}

	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

	public int getClientId() {
		return clientId;
	}

	public List<AccountManager> getAccountManagerList() {
		return accountManagerList;
	}

	public void setAccountManagerList(List<AccountManager> accountManagerList) {
		this.accountManagerList = accountManagerList;
	}

	
	@Override
	public String toString() {
		List<Integer> formIdList = new ArrayList<>();
		this.formList.forEach((x) -> formIdList.add(x.getFormId()));
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", formList=" + formIdList + "]";
	}

}
