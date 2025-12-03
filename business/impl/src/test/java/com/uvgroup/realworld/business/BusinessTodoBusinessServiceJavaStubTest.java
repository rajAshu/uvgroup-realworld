package com.uvgroup.realworld.business;

import static org.junit.Assert.assertEquals;

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

@Configuration
@ComponentScan(basePackages = {
		"com.uvgroup.realworld.business.impl",
		"com.uvgroup.realworld.data.stub" })
class SpringTestContext {
}

// 1. We need to test using Spring
// 2. How do we tell Spring to use specific Configuration
// 3. How do autowire the BusinessTodoBusinessService
// 4. How do we auto wire BusinessTodoDataServiceStub
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestContext.class)
public class BusinessTodoBusinessServiceJavaStubTest {

	@Autowired
	BusinessTodoBusinessService businessService;

	@Test
	public void testGetBusinessTodosAboutSpring() {
		List<BusinessTodo> BusinessTodos = businessService
				.retrieveBusinessTodosRelatedToSpring("Ranga");
		System.out.println(BusinessTodos);
		assertEquals(2, BusinessTodos.size());
	}
}