package com.tactfactory.nikonikoweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.generation.InitDatabase;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;
import com.tactfactory.nikonikoweb.models.security.SecurityLogin;

@Controller
@RequestMapping(RootController.BASE_URL)
public class RootController {

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IFunctionCrudRepository functionCrud;

	@Autowired
	private IAbilityCrudRepository abilityCrud;

	private String login;
	private String password;

	
	
	
	public final static String BASE_URL = "/";

//	@RequestMapping(value = { "" }, method = RequestMethod.GET)
//	public String index(Model model) {
//		System.out.println("root/login");
//		return "root/login";
//	}

	InitDatabase initDatabase;
	
	@RequestMapping(value = { "init" }, method = RequestMethod.GET)
	public String initGet(Model model) {
		initDatabase = new InitDatabase();
		functionCrud.deleteAll();
		abilityCrud.deleteAll();
		userCrud.deleteAll();
		return "root/init";
	}
	
	@RequestMapping(value = { "init" }, method = RequestMethod.POST)
	public String initPost(Model model) {
		ArrayList<Function> functions = initDatabase.getFunctionList();
		ArrayList<Ability> abilities= initDatabase.getAbilityList();
		User admin = initDatabase.getUserAdmin();
		
		
		for(Function function : functions) {
				functionCrud.save(function);
		}
		for(Ability ability : abilities) {
			abilityCrud.save(ability);
		}
		userCrud.save(admin);
		
		
		return "redirect:/login";
	}
	
	
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
		
		List<User> users = userCrud.findByLogin("admin");
		User admin = null;
		for(User user : users) {
			admin = user;
			break;
		}

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
