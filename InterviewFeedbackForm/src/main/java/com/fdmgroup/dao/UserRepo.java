package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.User;

public interface UserRepo extends CrudRepository<User,Integer>{
	List<User> findByUsername(String username);
	List<User> findByEmail(String email);
}
