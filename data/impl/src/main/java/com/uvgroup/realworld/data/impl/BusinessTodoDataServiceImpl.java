package com.uvgroup.realworld.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uvgroup.realworld.data.api.BusinessTodoDataService;
import com.uvgroup.realworld.data.BusinessDatabase.BusinessDatabase;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

@Component("TodoDataService")
public class BusinessTodoDataServiceImpl implements BusinessTodoDataService {

	@Autowired
	private BusinessDatabase BusinessDatabase;

	@Override
	public List<BusinessTodo> retrieveBusinessTodos(String userName) {
		return BusinessDatabase.retrieveBusinessTodos(userName);
	}
}
