package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityLogin;


@Controller
@RequestMapping(RootController.BASE_URL)
public class RootController {

	private String login;
	private String password;
	
	public final static String BASE_URL = "/";

//	@RequestMapping(value = { "" }, method = RequestMethod.GET)
//	public String index(Model model) {
//		System.out.println("root/login");
//		return "root/login";
//	}

	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String loginGet(Model model) {
		System.out.println("root/login (GET)");
		this.login="";
		this.password="";
		model.addAttribute("login",this.login);
		model.addAttribute("password",this.password);
		return "root/login";
	}
	
	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public String loginPost(@ModelAttribute SecurityLogin securityLogin,
			Model model) {
		//Map<String, Object> map = model.asMap();
		
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("1234");
		
		if((admin.getLogin().equals(securityLogin.getLogin())) && 
			(admin.getPassword().equals(securityLogin.getPassword()))) {
			return "redirect:/admin";
		}
		
		return "redirect:/login";
	}

	@RequestMapping(value = { "admin" }, method = RequestMethod.GET)
	public String adminGet(Model model) {
		return "root/admin";
	}
}
