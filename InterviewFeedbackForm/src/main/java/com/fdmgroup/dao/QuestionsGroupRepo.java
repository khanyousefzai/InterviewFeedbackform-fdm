package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Question;
import com.fdmgroup.model.QuestionsGroup;
import com.fdmgroup.model.User;

public interface QuestionsGroupRepo extends CrudRepository<QuestionsGroup,Integer> {


	QuestionsGroup findById(int id);
	QuestionsGroup findByUserAndQuestion(User user, Question question);
	List<QuestionsGroup> findByUser(User user);
	QuestionsGroup findByQuestion(Question question);
	
}
