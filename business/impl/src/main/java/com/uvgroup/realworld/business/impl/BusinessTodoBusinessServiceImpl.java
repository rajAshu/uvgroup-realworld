package com.uvgroup.realworld.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import com.uvgroup.realworld.business.api.BusinessTodoBusinessService;
import com.uvgroup.realworld.data.api.BusinessTodoDataService;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

@Component
public class BusinessTodoBusinessServiceImpl implements BusinessTodoBusinessService {

	@Autowired
	@Qualifier("TodoDataService")
	BusinessTodoDataService BusinessTodoDs;

	@Override
	public List<BusinessTodo> retrieveBusinessTodosRelatedToSpring(String user) {

		List<BusinessTodo> BusinessTodos = BusinessTodoDs.retrieveBusinessTodos(user);
		List<BusinessTodo> filteredBusinessTodos = filterBusinessTodosRelatedToSpring(BusinessTodos);

		return filteredBusinessTodos;

	}

	// NOTE : We get everything from database and filter
	// instead of filtering in the database. Not the most efficient
	// implementation.
	private List<BusinessTodo> filterBusinessTodosRelatedToSpring(List<BusinessTodo> BusinessTodos) {

		List<BusinessTodo> filteredBusinessTodos = new ArrayList<BusinessTodo>();
		for (BusinessTodo BusinessTodo : BusinessTodos) {
			if (BusinessTodo.getDesc().contains("Spring")) {
				filteredBusinessTodos.add(BusinessTodo);
			}
		}

		return filteredBusinessTodos;
	}

}
