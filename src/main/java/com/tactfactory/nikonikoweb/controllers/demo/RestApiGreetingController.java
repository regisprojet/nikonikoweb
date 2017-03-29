package com.tactfactory.nikonikoweb.controllers.demo;

import java.io.IOException;
import java.util.List;

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

import com.mysql.fabric.Response;
import com.tactfactory.nikonikoweb.dao.IGreetingCrudRepository;
import com.tactfactory.nikonikoweb.models.demo.Greeting;

@RestController
public class RestApiGreetingController {
	@Autowired
	private IGreetingCrudRepository greetingCrud ;

	// Rest Actions
	@RequestMapping(path="/demo/greeting", method=RequestMethod.GET)
	public Iterable<Greeting> listAction() {

		return this.greetingCrud.findAll();
	}

	// list
	// -----
	@RequestMapping(path="/demo/greeting/list", method=RequestMethod.GET)
	public Iterable<Greeting> listAction(
			HttpServletResponse response,
			@RequestParam int offset,
			@RequestParam int limit) throws IOException  {

			if(limit<= 0) {
				response.sendError(
						HttpStatus.BAD_REQUEST.value(),
						"Invalid limit and/or offset.");
				return null;
			}
		Pageable pageable = new PageRequest(offset /limit, limit);

		return this.greetingCrud.findAll(pageable);

	}
	// Create, Read, Update, Delete , show
	// -----------------------------------
	@RequestMapping(path="/demo/greeting/{id}/show", method=RequestMethod.GET)
	public Greeting showAction(HttpServletResponse response,
			@PathVariable ("id") Long id) {
		Greeting greeting = greetingCrud.findOne(id);

		if(greeting == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

		return greeting;
	}

	@RequestMapping(path="/demo/greeting/create", method=RequestMethod.POST)
	public Greeting createAction(
			HttpServletResponse response,
			@RequestParam String content) throws BadHttpRequest, IOException {

		return newOrEdit(response, content, new Greeting());
	}

	@RequestMapping(path="/demo/greeting/{id}/delete", method=RequestMethod.DELETE)
	public void deleteAction(
			HttpServletResponse response,
			@PathVariable ("id") Long id) throws BadHttpRequest, IOException {
		Greeting greeting = greetingCrud.findOne(id);

		if(greeting != null) {
			this.greetingCrud.delete(id);
			response.setStatus(HttpStatus.NO_CONTENT.value());
		} else	{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}

	@RequestMapping(path="/demo/greeting/{id}/edit", method=RequestMethod.PUT)
	public Greeting editAction(
			HttpServletResponse response,
			@PathVariable ("id") Long id,

			@RequestParam(value = "content", required =true) String content) throws BadHttpRequest, IOException {

		return newOrEdit(response, content, greetingCrud.findOne(id));
	}

	private Greeting newOrEdit(HttpServletResponse response,
			String content, Greeting greeting) throws IOException {
    	if (content.equals(null) || content.equals("")) {
	    	response.sendError(HttpStatus.BAD_REQUEST.value(), "Require name");
	    	return null;
		}

    	if (!content.toLowerCase().contains("hello")) {
        	content = "Hello " + content;
    	}

		greeting.setContent(content);
		if(greeting.getId() == null) {
			response.setStatus(HttpStatus.CREATED.value());
		}

		this.greetingCrud.save(greeting);
		return greeting;
	}


	@RequestMapping(path="/demo/greeting/addBourrin", method=RequestMethod.GET)
	public Greeting greeting(@RequestParam(value="name", defaultValue="world" ) String name) {
		Greeting greeting = new Greeting(String.format("Hello %s!", name));

		this.greetingCrud.save(greeting);

		return greeting;
	}
}
