package com.uvgroup.realworld.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.stub;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.uvgroup.realworld.business.impl.BusinessTodoBusinessServiceImpl;
import com.uvgroup.realworld.data.api.BusinessTodoDataService;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

@RunWith(MockitoJUnitRunner.class)
public class BusinessTodoBusinessServiceMockitoTest {

	@Mock
	private BusinessTodoDataService BusinessTodoDs;

	@InjectMocks
	private BusinessTodoBusinessServiceImpl BusinessTodoBs;

	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRetrieveBusinessTodosRelatedToSpring() {

		List<BusinessTodo> BusinessTodos = Arrays.asList(new BusinessTodo(
				"Mockito - Complete Spring Tutorial", new Date(), false),
				new BusinessTodo("Mockito - Complete Spring MVC Tutorial", new Date(),
						false), new BusinessTodo("Mockito - Complete All Tutorials",
								new Date(), false));

		stub(BusinessTodoDs.retrieveBusinessTodos(anyString())).toReturn(BusinessTodos);

		List<BusinessTodo> filteredBusinessTodos = BusinessTodoBs
				.retrieveBusinessTodosRelatedToSpring("dummyUser");

		assertEquals(2, filteredBusinessTodos.size());
	}
}