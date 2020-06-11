package com.fdmgroup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Report;

public interface ReportRepo extends CrudRepository<Report, Integer>{
	public List<Report> findByrStatus(String status);
}
