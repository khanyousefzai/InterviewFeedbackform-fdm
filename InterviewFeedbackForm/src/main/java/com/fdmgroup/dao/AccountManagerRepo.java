package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.AccountManager;



public interface AccountManagerRepo extends CrudRepository<AccountManager,Integer> {
	public List<AccountManager> findByUsername(String username);

}
