package com.uvgroup.realworld.data.api;

import java.util.List;

import com.uvgroup.realworld.model.api.client.BusinessTodo;

public interface BusinessTodoDataService {
	List<BusinessTodo> retrieveBusinessTodos(String userName);
}
