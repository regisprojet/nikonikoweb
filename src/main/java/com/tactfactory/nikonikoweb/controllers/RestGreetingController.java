package com.tactfactory.nikonikoweb.controllers;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import javassist.tools.web.BadHttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tactfactory.nikonikoweb.dao.IGreetingCrudRepository;
import com.tactfactory.nikonikoweb.models.Greeting;

@RestController
public class RestGreetingController {

	@Autowired
	IGreetingCrudRepository repository;
	

//	@RequestMapping(path = "/demo/greeting", method=RequestMethod.GET)
//	public Iterable<Greeting> listActionGet() {
//			return this.repository.findAll();
//    }	

	@RequestMapping(path = "/demo/greeting", method=RequestMethod.GET)
	public Iterable<Greeting> listActionPost(
			@RequestParam("limit") int limit,
			@RequestParam("offset") int offset,
			HttpServletResponse response
			
			) throws IOException {
		
			if(limit <= 0 || offset <=0) {
				response.sendError(HttpStatus.BAD_REQUEST.value(),
						"Invalid limit and/or offset");
			}
			Pageable pageable = new PageRequest(offset/limit, limit);
			
			return this.repository.findAll(pageable);
    }	

	@RequestMapping(path = "/demo/greeting/{id}", method=RequestMethod.DELETE)
	public void deleteAction(
			@PathVariable Long id,
			HttpServletResponse response
     ) {
		if(this.repository.exists(id)) {
			this.repository.delete(id);
			response.setStatus(HttpStatus.NO_CONTENT.value());
		}
		else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}

	@RequestMapping(path = "/demo/greeting/{id}/show", method=RequestMethod.GET)
	public Greeting showAction(
			@PathVariable("id") Long id,
			HttpServletResponse response
     ) throws IOException {

		Greeting greeting = this.repository.findOne(id);
		
		if(greeting==null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			//response.sendError(404);
		}
		return greeting;
	}
	
	
	@RequestMapping(path = "/demo/greeting/{id}/edit", method=RequestMethod.PUT)
	public Greeting editAction(
			@PathVariable("id") Long id,
			@RequestParam("content") String content,
			HttpServletResponse response
     ) throws IOException {
		
//		if(content.equals("")) {
//			response.sendError(HttpStatus.BAD_REQUEST.value(),"content cannot be null");
//		}
		
		Greeting greeting = repository.findOne(id);
//		greeting.setContent(content);
//		repository.save(greeting);
		return newOrEdit(response, content, greeting);
	}

	private Greeting newOrEdit(HttpServletResponse response, String content, Greeting greeting) throws IOException {
		
		
		if(content.equals("")) {
				response.sendError(HttpStatus.BAD_REQUEST.value(),"Required name");
				return null;	
		}
		
		if(!content.toLowerCase().contains("hello")) {
			content = "hello "+ content;
		}
		
		greeting.setContent(content);
		if(greeting.getId()==null) {
			response.setStatus(HttpStatus.CREATED.value());
		}
		this.repository.save(greeting);
		return greeting;
	}
	
	@RequestMapping(path = "/demo/greeting", method=RequestMethod.POST)
	public Greeting createAction(
			HttpServletResponse response,
			@RequestParam String content
			) 
	throws BadHttpRequest, IOException
	{
			if(content==null || content.equals("")) {
				response.sendError(400, "pas content");
				throw new BadHttpRequest();
			}
		//if(!content.toLowerCase().contains("h"))
			//response.setStatus(201);
			Greeting greeting = new Greeting(content);
			this.repository.save(greeting);
			return greeting;
	}
	
	@RequestMapping(path = "/demo/greeting/add", method=RequestMethod.GET)
	public Greeting addGreeting(
			@RequestParam(value="name", defaultValue="") String name
			) {
		StringBuilder db = new StringBuilder();
		String sb =db.append("Hello ").append(name).toString();
		Greeting greeting 	 = new Greeting(String.format("Hello %s", name) + " " + sb);
		this.repository.save(greeting);
		return greeting;
	}
	
	
}
