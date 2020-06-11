package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Expertise;


public interface ExpertiseRepo extends CrudRepository<Expertise,Integer> {
	List<Expertise> findByExpertiseName(String expertise);
}
