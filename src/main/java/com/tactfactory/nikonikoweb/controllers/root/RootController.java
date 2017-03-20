package com.tactfactory.nikonikoweb.controllers.root;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IAgencyCrudRepository;
import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.environment.Environment;
import com.tactfactory.nikonikoweb.generation.InitDatabase;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Agency;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.Pole;
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

	@Autowired
	private IPoleCrudRepository poleCrud;

	@Autowired
	private IAgencyCrudRepository agencyCrud;

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
		userCrud.deleteAll();
		poleCrud.deleteAll();
		agencyCrud.deleteAll();
		functionCrud.deleteAll();
		abilityCrud.deleteAll();
		return "root/init";
	}
	
	@RequestMapping(value = { "init" }, method = RequestMethod.POST)
	public String initPost(Model model) {
		ArrayList<Function> functions = initDatabase.getFunctionList();
		ArrayList<Ability> abilities= initDatabase.getAbilityList();
		ArrayList<User> admins = initDatabase.getAdminList();
		ArrayList<User> devs = initDatabase.getDevList();
		ArrayList<Pole> poles = initDatabase.getPoleList();
		ArrayList<Agency> agencies = initDatabase.getAgencyList();
			
		
		for(Function function : functions) {
				functionCrud.save(function);
		}
		for(Ability ability : abilities) {
			System.out.println("ability : " + ability);
			abilityCrud.save(ability);
		}
		for(Pole pole : poles) {
			System.out.println("pole : " + pole );
			poleCrud.save(pole);
		}
		for(Agency agency : agencies) {
			System.out.println("agency : " + agency );
			agencyCrud.save(agency);
		}
		for(User admin : admins) {
			userCrud.save(admin);
		}
		
		for(User dev : devs) {
			userCrud.save(dev);
		}
		
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
		Environment environment = Environment.getInstance();
		
		List<User> users = userCrud.findByLogin(securityLogin.getLogin());
		for(User user : users) {
			if(securityLogin.getPassword().equals(user.getPassword())) {
				environment.setCurrentUser(user);
				String functionName = userCrud.functionById(user.getId());
				System.out.println(user+", fonction = "+functionName);
				if(functionName.equals("administrateur")) {
					return "redirect:/admin";
				}
				if(functionName.equals("developpeur")) {
					return "redirect:/user";
				}
			}
		}

		return "redirect:/login";
	}

	@RequestMapping(value = { "admin" }, method = RequestMethod.GET)
	public String adminGet(Model model) {
		return "root/admin";
	}
	
	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public String userGet(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		model.addAttribute("userName", currentUser.getFirstname() + " " + currentUser.getLastname());
		
		return "root/user";
	}

}
