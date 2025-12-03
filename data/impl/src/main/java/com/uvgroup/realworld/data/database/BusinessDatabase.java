package com.uvgroup.realworld.data.BusinessDatabase;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uvgroup.realworld.model.api.client.BusinessTodo;

//BusinessTodo : Ideally should use jdbc interfacing or an ORM
// A Dummy BusinessDatabase just to quickly work something out

@Component
public class BusinessDatabase {
	public List<BusinessTodo> retrieveBusinessTodos(String userName) {

		List<BusinessTodo> BusinessTodos = Arrays.asList(new BusinessTodo(
				"Real BusinessDatabase - Complete Spring Tutorial", new Date(), false),
				new BusinessTodo("Real BusinessDatabase - Complete Spring MVC Tutorial",
						new Date(), false), new BusinessTodo(
						"Real BusinessDatabase - Complete All Tutorials", new Date(),
						false));

		return BusinessTodos;
	}

}
