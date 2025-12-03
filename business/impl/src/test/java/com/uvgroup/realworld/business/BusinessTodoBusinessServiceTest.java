package com.uvgroup.realworld.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uvgroup.realworld.business.api.BusinessTodoBusinessService;
import com.uvgroup.realworld.model.api.client.BusinessTodo;

//Application Context - java
//Business Impl - com.uvgroup.realworld.business.impl
//Stub - com.uvgroup.realworld.data.stub

@Configuration
@ComponentScan(basePackages = {
		"com.uvgroup.realworld.business.impl",
		"com.uvgroup.realworld.data.stub" })
class SpringApplicationContextTest {

}

// Spring
@RunWith(SpringJUnit4ClassRunner.class)
// Application Context
@ContextConfiguration(classes = SpringApplicationContextTest.class)
public class BusinessTodoBusinessServiceTest {

	@Autowired
	BusinessTodoBusinessService businessService;

	@Test
	public void testRetrieveBusinessTodosRelatedToSpring() {
		List<BusinessTodo> BusinessTodos = businessService
				.retrieveBusinessTodosRelatedToSpring("Rajeev");
		System.out.println(BusinessTodos);
		assertEquals(2, BusinessTodos.size());
	}

	@Test
	public void testBusinessTodoBusinessServiceIsProperlyLoaded() {
		assertNotNull(businessService);
	}

}
