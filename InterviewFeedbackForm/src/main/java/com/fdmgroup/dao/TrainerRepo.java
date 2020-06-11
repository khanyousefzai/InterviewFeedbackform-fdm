package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Trainer;

public interface TrainerRepo extends CrudRepository<Trainer,Integer>{
	List<Trainer> findByUsername(String username);
}
