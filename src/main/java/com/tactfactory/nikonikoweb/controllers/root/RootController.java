package com.tactfactory.nikonikoweb.controllers.root;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IAgencyCrudRepository;
import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.environment.Environment;
import com.tactfactory.nikonikoweb.generation.InitDatabase;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Agency;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.User;
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
		ArrayList<User> vips = initDatabase.getVipList();
		ArrayList<User> chefProjets = initDatabase.getChefProjetList();
		ArrayList<Pole> poles = initDatabase.getPoleList();
		ArrayList<Agency> agencies = initDatabase.getAgencyList();


		for(Ability ability : abilities) {
			System.out.println("ability : " + ability);
			abilityCrud.save(ability);
		}

		for(Function function : functions) {
				System.out.println("function save : "+ function.getName());
				functionCrud.save(function);
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
		for(User vip : vips) {
			userCrud.save(vip);
		}
		for(User chefProjet : chefProjets) {
			userCrud.save(chefProjet);
		}

		return "redirect:/login";
	}


	@RequestMapping(path = { BASE_URL, "login" }, method = RequestMethod.GET)
	public String loginGet(Model model) {
		System.out.println("root/login (GET)");
		this.login="";
		this.password="";
		model.addAttribute("login",this.login);
		model.addAttribute("password",this.password);
		return "root/connection";
	}

	@RequestMapping(path = { "login" }, method = RequestMethod.POST)
	public String loginPost(@ModelAttribute SecurityLogin securityLogin,
			Model model) {
		//Map<String, Object> map = model.asMap();
		Environment environment = Environment.getInstance();

		List<User> users = userCrud.findByLogin(securityLogin.getLogin());

		for(User user : users) {
			if(securityLogin.getPassword().equals(user.getPassword())) {
				environment.setCurrentUser(user);
				String functionName = null;//userCrud.functionById(user.getId());

				Set<BigInteger>ids = userCrud.functionIdsById(user.getId());
				Set<Function> functions = new HashSet<Function>();

				String string = "";
				for(BigInteger id : ids) {
					Function function = functionCrud.findOne(id.longValue());
					functions.add(function);
					string += function.getName() + " ";
				}
				environment.setFunctions(string);
				environment.setAllFunctions(functions);

				ids = userCrud.abilitiesById(user.getId());
				Set<Ability> abilities = new HashSet<Ability>();
				string = "";
				for(BigInteger id : ids) {
					Ability ability = abilityCrud.findOne(id.longValue());
					abilities.add(ability);
					string += ability.getName() + " ";
				}

				environment.setAllAbilities(abilities);
				environment.setAbilities(string);

				if(functions.size()==1) {
					functionName=null;
					for(Function function : functions ) {
						functionName = function.getName();
						break;
					}
					if(functionName.equals("administrateur")) {
						return "redirect:/admin";
					}
					if(functionName.equals("developpeur") || functionName.equals("chef de projet"))  {
						return "redirect:/user";
					}
					if(functionName.equals("vip")) {
						return "redirect:/vip";
					}
				}
				if(functions.size()>1) {
					return "redirect:/multifunction";
				}
			}
		}

		return "redirect:/login";
	}

	@RequestMapping(value = { "admin" }, method = RequestMethod.GET)
	public String adminGet(Model model) {
		Environment environment = Environment.getInstance();
		model.addAttribute("abilities", environment.getAbilities());
		return "root/admin";
	}

	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public String userGet(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		model.addAttribute("userName", currentUser.getFirstname()
				+ " " + currentUser.getLastname().toUpperCase());
		model.addAttribute("abilities", environment.getAbilities());

		model.addAttribute("functions", environment.getFunctions());
		Pole pole = poleCrud.findOne(userCrud.poleIdById(currentUser.getId()).longValue());
		model.addAttribute("pole", pole.getName());

		return "root/user";
	}


	@RequestMapping(value = { "multifunction" }, method = RequestMethod.GET)
	public String multifunctionGet(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		model.addAttribute("userName", currentUser.getFirstname()
				+ " " + currentUser.getLastname().toUpperCase());
		model.addAttribute("abilities", environment.getAbilities());
		model.addAttribute("functions", environment.getFunctions());
		BigInteger id= userCrud.poleIdById(currentUser.getId());
		if(id==null) {
			model.addAttribute("pole", "");
		}
		else {
			Pole pole = poleCrud.findOne(id.longValue());

			model.addAttribute("pole", pole.getName());
		}
		return "root/multifunction";
	}

	@RequestMapping(value = { "vip" }, method = RequestMethod.GET)
	public String vipGet(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		model.addAttribute("userName", currentUser.getFirstname()
				+ " " + currentUser.getLastname().toUpperCase());
		model.addAttribute("abilities", environment.getAbilities());

		return "root/vip";
	}

}
