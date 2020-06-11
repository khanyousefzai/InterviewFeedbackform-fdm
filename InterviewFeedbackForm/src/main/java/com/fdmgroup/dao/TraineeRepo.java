package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Trainee;

public interface TraineeRepo extends CrudRepository<Trainee,Integer>{
	List<Trainee> findByUsername(String username);
}
