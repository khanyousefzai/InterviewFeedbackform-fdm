package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Admin;

public interface AdminRepo extends CrudRepository<Admin, Integer> {
	public List<Admin> findByUsername(String username);
}
