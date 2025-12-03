package com.uvgroup.realworld.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = { "com.uvgroup.realworld.business" })
class SpringRealworldBusinessContext {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringRealworldBusinessContext.class)
public class BusinessTodoBusinessClassTest {

	@Autowired
	BusinessTodoBusinessService2 businessService;

	@Test
	public void testGetBusinessTodosAboutSpring() {
		List<String> BusinessTodos = businessService.retrieveBusinessTodosRelatedToSpring("Ranga");
		assertEquals(1, BusinessTodos.size());
		assertEquals("Learn Spring", BusinessTodos.get(0));
	}
}

@Component
class BusinessTodoBusinessService2 {

	@Autowired
	BusinessTodoDataService2 dataService;

	List<String> retrieveBusinessTodosRelatedToSpring(String user) {
		List<String> BusinessTodosRelatedToSpring = new ArrayList<String>();

		List<String> BusinessTodos = dataService.retrieveBusinessTodos(user);

		for (String BusinessTodo : BusinessTodos) {
			if (BusinessTodo.contains("Spring")) {
				BusinessTodosRelatedToSpring.add(BusinessTodo);
			}
		}
		return BusinessTodosRelatedToSpring;
	}

}

class BusinessTodoDataServiceStub2 implements BusinessTodoDataService2 {
	public List<String> retrieveBusinessTodos(String user) {
		return Arrays.asList("Learn Spring", "Learn Struts", "Learn to Dance");
	}
}

interface BusinessTodoDataService2 {
	List<String> retrieveBusinessTodos(String user);
}

@Component
class BusinessTodoDataServiceRealImpl implements BusinessTodoDataService2 {
	public List<String> retrieveBusinessTodos(String user) {
		return BusinessDatabase2.retrieveBusinessTodos(user);
	}
}

class BusinessDatabase2 {
	static List<String> retrieveBusinessTodos(String user) {
		//throw new RuntimeException("BusinessDatabase Down");
		return Arrays.asList("Learn Spring","Learn Struts","Learn to Dance");
	}
}