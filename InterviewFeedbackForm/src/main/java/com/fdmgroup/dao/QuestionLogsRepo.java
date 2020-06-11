package com.fdmgroup.dao;


import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.model.Question;
import com.fdmgroup.model.QuestionLogs;

public interface QuestionLogsRepo extends CrudRepository<QuestionLogs,Integer> {

	public Iterable<QuestionLogs> findAllByQuestionOrderByMessageDateAsc(Question question);
}
