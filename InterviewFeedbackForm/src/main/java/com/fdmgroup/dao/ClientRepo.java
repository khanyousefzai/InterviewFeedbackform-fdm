package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Client;


public interface ClientRepo extends CrudRepository<Client,Integer> {
	
	public List<Client> findByClientName(String clientString);
	
	public List<Client> findByClientNameIgnoreCase(String clientString);

}
