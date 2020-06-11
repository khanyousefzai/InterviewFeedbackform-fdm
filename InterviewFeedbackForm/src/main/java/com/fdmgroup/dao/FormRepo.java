package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.User;


public interface FormRepo extends CrudRepository<Form,Integer>{
	List<Form> findByAuthor(User user);
	List<Form> findAll();
	public List<Form> findAllByOrderByFormIdDesc();
	Form findById(int id);
	List<Form> findByClientDivision(String dList);
	public List<Form> findAllByOrderByInterviewDateDesc();
	public List<Form> findByAuthorOrderByInterviewDateDesc(User user);
	
}
