package com.uvgroup.realworld.business.api;

import java.util.List;

import com.uvgroup.realworld.model.api.client.BusinessTodo;

public interface BusinessTodoBusinessService {
	List<BusinessTodo> retrieveBusinessTodosRelatedToSpring(String user);
}
