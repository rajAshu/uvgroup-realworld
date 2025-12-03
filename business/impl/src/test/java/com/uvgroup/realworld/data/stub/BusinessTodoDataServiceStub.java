package com.uvgroup.realworld.data.stub;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uvgroup.realworld.data.api.BusinessTodoDataService;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

@Component("TodoDataServiceStub")
public class BusinessTodoDataServiceStub implements BusinessTodoDataService {

	@Override
	public List<BusinessTodo> retrieveBusinessTodos(String userName) {

		List<BusinessTodo> BusinessTodos = Arrays.asList(new BusinessTodo(
				"Stub - Complete Spring Tutorial", new Date(), false),
				new BusinessTodo("Stub - Complete Spring MVC Tutorial", new Date(),
						false), new BusinessTodo("Stub - Complete All Tutorials",
						new Date(), false));

		return BusinessTodos;
	}
}
