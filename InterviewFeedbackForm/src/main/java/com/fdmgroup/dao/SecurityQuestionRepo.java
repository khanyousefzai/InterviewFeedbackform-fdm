package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.fdmgroup.model.SecurityQuestion;

public interface SecurityQuestionRepo extends CrudRepository<SecurityQuestion,Integer>{
	
}
