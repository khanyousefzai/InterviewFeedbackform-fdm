package com.fdmgroup.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Form;
import com.fdmgroup.model.Question;



public interface QuestionRepo extends CrudRepository<Question,Integer> {

	public Question findById(int id);
	public List<Question> findAllByOrderByDislikesDesc();
	public List<Question> findAllByOrderByLikesDesc();
	public List<Question> findAllByOrderByQuestionBody();
	public List<Question> findAllByOrderByQuestionId();
	public Question findByQuestionBody(String questionBody);

}
