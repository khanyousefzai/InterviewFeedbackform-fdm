package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Tag;


public interface TagRepo extends CrudRepository<Tag,Integer> {
	
	List<Tag> findByTagNameIgnoreCase(String tagname);

	List<Tag> findByTagName(String tagname);
}
