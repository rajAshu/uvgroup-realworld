package com.uvgroup.realworld.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uvgroup.realworld.business.api.BusinessTodoBusinessService;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/BusinessApplicationContext.xml" })
public class BusinessTodoBusinessServiceXmlStubTest {

	@Autowired
	private BusinessTodoBusinessService BusinessTodoBusinessService;

	@Test
	public void testClientProductSum() {
		List<BusinessTodo> BusinessTodos = BusinessTodoBusinessService
				.retrieveBusinessTodosRelatedToSpring("dummyUser");
		System.out.println(BusinessTodos);
		assertEquals(2, BusinessTodos.size());
	}
}