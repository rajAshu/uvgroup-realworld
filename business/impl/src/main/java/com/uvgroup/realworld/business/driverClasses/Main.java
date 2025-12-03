package com.uvgroup.realworld.business.driverClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.uvgroup.realworld.business.api.BusinessTodoBusinessService;

@Configuration
@ComponentScan(basePackages = { "com.uvgroup" })
class SpringContext {
}

@Component
public class Main {

	@Autowired
	private BusinessTodoBusinessService BusinessTodoBusinessService;

	private void runBusinessService() {
		System.out.println(BusinessTodoBusinessService.retrieveBusinessTodosRelatedToSpring("Rajeev"));
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
		Main p = context.getBean(Main.class);
		p.runBusinessService();
	}

}