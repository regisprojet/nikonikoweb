package com.tactfactory.nikonikoweb.controllers.root;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IAgencyCrudRepository;
import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IGreetingCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.environment.Environment;
import com.tactfactory.nikonikoweb.generation.InitDatabase;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Agency;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.Greeting;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityLogin;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

@Controller
@SessionAttributes("thought")
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

	@Autowired
	private IGreetingCrudRepository greetingCrud;

	private String login;
	private String password;




	public final static String BASE_URL = "/";

//	@RequestMapping(value = { "" }, method = RequestMethod.GET)
//	public String index(Model model) {
//		System.out.println("root/login");
//		return "root/login";
//	}

	InitDatabase initDatabase;
	@RequestMapping(value = { "CreateNiknikoSql" }, method = RequestMethod.GET)
	public String CreateNiknikoSql(Model model) {
		ArrayList<User> users = (ArrayList<User>) userCrud.findAllByRoleId(15l);
		try {BufferedWriter fichier = new BufferedWriter(new FileWriter("d:/test.sql"));

		String satisfaction[] = {"0","1","1","1","2","2","3","3","1","1"};
		String comment[] = {"", "ca va!"," genial" ,"codage reussi", "pas top", "codage a refaire", "malade", "fatigue", "en pleine forme", "projet valide par l'equipe"};


		Random randomGenerator = new Random();
		DateTime dateJour = new DateTime();
		DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");

		for(int i=0; i<90; i++) {
			dateJour = dateJour.minusDays(1);
			for (User user : users) {
				int rand = randomGenerator.nextInt(10);
				fichier.write("INSERT INTO nikoniko (nikoniko_comment,isanonymous,log_date,satisfaction,user_id)\n");
				String value = "\""+comment[rand]+"\","+ true +",\"" + dtf.print(dateJour) + "\"," + satisfaction[rand] + "," + user.getId() ;
				fichier.write("\tVALUE ( " + value + ");\n");

			}
		}
		fichier.close();
		} catch (IOException e) {e.printStackTrace();}

		return "redirect:/login";
	}

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


	@RequestMapping(path = { "/login2" }, method = RequestMethod.GET)
	public String loginGet(Model model) {
		System.out.println("root/login (GET)");
		this.login="";
		this.password="";
		model.addAttribute("login",this.login);
		model.addAttribute("password",this.password);
		return "root/connection";
	}

	@RequestMapping(path = { "login2" }, method = RequestMethod.POST)
	public String login2Post(@ModelAttribute SecurityLogin securityLogin,
			Model model) {
		//Map<String, Object> map = model.asMap();
		Environment environment = Environment.getInstance();

		List<User> users = userCrud.findAllByLogin(securityLogin.getLogin());

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
						return "redirect:/inputNiko";
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


	@RequestMapping(path = { "logout2" }, method = RequestMethod.GET)
	public String logoutGet(@ModelAttribute SecurityLogin securityLogin,
			Model model) {
		Environment environment = Environment.getInstance();
		environment.reset();
		return "redirect:/login";
	}

	@RequestMapping(value = { "/admin2" }, method = RequestMethod.GET)
	public String adminGet(Model model) {
		Environment environment = Environment.getInstance();
		model.addAttribute("abilities", environment.getAbilities());
		return "root/admin";
	}


	@Secured(value={"ROLE_ADMIN","ROLE_USER", "ROLE_VIP"})
	@RequestMapping(value = { "/user2" }, method = RequestMethod.GET)
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

	@Secured(value={"ROLE_ADMIN","ROLE_USER", "ROLE_VIP"})
	@RequestMapping(value = { "/multifunction" }, method = RequestMethod.GET)
	public String multifunctionGet(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("username", user.getFirstname()
				+ " " + user.getLastname().toUpperCase());
		return "root/multifunction";
	}

	@RequestMapping(path = { "/", "/redirect" }, method = RequestMethod.GET)
	public String redirect(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userDetails==null) {
			return "security/login";
		}
		User user = userCrud.findByLogin(userDetails.getUsername());
		model.addAttribute("username", user.getFirstname() + " " + user.getLastname());

		if(user.getRoles().size()>1) {
			System.out.println("plusieurs roles");
			return "root/multifunction";
	    }
		for (SecurityRole role : user.getRoles()) {
			if(role.getRole().equals("ROLE_ADMIN")) {
				return "redirect:/admin/user/index";
			}
			else if(role.getRole().equals("ROLE_USER")) {
				return "redirect:/inputNiko";
			}
			else if(role.getRole().equals("ROLE_VIP")) {
				return "redirect:/vip";
			}
		}

		return "root/redirect";
	}

	@Secured(value={"ROLE_VIP"})
	@RequestMapping(value = { "/vip" }, method = RequestMethod.GET)
	public String vipGet(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("username", user.getFirstname()
				+ " " + user.getLastname().toUpperCase());
		//model.addAttribute("abilities", environment.getAbilities());

		return "root/vip";
	}

	@Secured(value={"ROLE_USER"})
	@RequestMapping(path = { "/home" }, method = RequestMethod.GET)
	public ModelAndView homeGet(/*@RequestParam String thoughtParam*/) {
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("thought", thoughtParam);
		modelAndView.setViewName("root/home");
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		modelAndView.addObject("username",user.getFirstname()+" "+user.getLastname());
		return modelAndView;
	}

	@Secured(value={"ROLE_USER"})
	@RequestMapping(path = { "/home" }, method = RequestMethod.POST)
	public String homePost(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("username",user.getFirstname()+" "+user.getLastname());
		return "redirect:/home2";
	}

	@Secured(value={"ROLE_USER"})
	@RequestMapping(path = { "/home2" }, method = RequestMethod.GET)
	public String home2Get(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("username",user.getFirstname()+" "+user.getLastname());
		return "root/home2";
	}


	@RequestMapping(path = { "/greeting" }, method = RequestMethod.GET)
	public String greeting(Model model) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		List<Greeting> greetings = greetingCrud.findAll();

		model.addAttribute("greetings",greetings);
		return "root/greeting";
	}

	// add by Régis
	@Secured(value={"ROLE_ADMIN","ROLE_USER", "ROLE_VIP"})
	@RequestMapping(path ="/loginbis", method =  RequestMethod.POST)
	public String loginBisPost(
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute SecurityLogin securityLogin, 	Model model

//    	HttpSession session, @ModelAttribute("SecurityLogin") @Valid SecurityLogin securityLogin,
//        BindingResult result, Model model, final RedirectAttributes redirectAttributes

        ) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());
		if(user.getRoles().size()>1) {
			System.out.println("plusieurs roles");
			return "redirect:/multifunction";
		}
		for (SecurityRole role : user.getRoles()) {
				if(role.getRole().equals("ROLE_ADMIN")) {
					return "redirect:/admin";
				}
				else if(role.getRole().equals("ROLE_USER")) {
					return "redirect:/inputNiko";
				}
				else if(role.getRole().equals("ROLE_VIP")) {
					return "redirect:/vip";
				}
		}
		return "redirect:/home";
	}

}
