package com.tactfactory.nikonikoweb.controllers.admin;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;







import com.tactfactory.nikonikoweb.controllers.application.ApplicationControleur;
//import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IAgencyCrudRepository;
//import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.ISecurityRoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.Agency;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(AdminController.BASE_URL)
public class AdminController extends ApplicationControleur {

	public final static String BASE_URL = "/admin2";

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IAgencyCrudRepository agencyCrud;

	@Autowired
	private IPoleCrudRepository poleCrud;

	@Autowired
	private ISecurityRoleCrudRepository roleCrud;


	@RequestMapping(path = { BASE_URL, "menu" }, method = RequestMethod.GET)
	public String menuGet(Model model) {

		return "admin/menu";
	}


	@RequestMapping(path = { BASE_URL, "adduser" }, method = RequestMethod.GET)
	public String adduserGet(Model model) {
		model.addAttribute("page", "ajout utilisateur");
		String[] fieldsManual =  { "login", "lastname", "firstname" };

		model.addAttribute("fields", fieldsManual);
		Map<String, Map<String,Object>> currentItem = DumpFields.fielderAdvance(
				DumpFields.createContentsEmpty(User.class),
				User.class);
		model.addAttribute("currentItem", currentItem);

		return "admin/adduser";
	}

	@RequestMapping(path = { BASE_URL, "adduser" }, method = RequestMethod.POST)
	public String adduserPost(@ModelAttribute("userForm") User user, Model model) {
		return "redirect:adduser";
	}


	@RequestMapping(path = { "index" }, method = RequestMethod.GET)
	public String indexGet(Model model) {
			UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User currentUser = userCrud.findByLogin(userDetails.getUsername());
			model.addAttribute("currentUserRoles", currentUser.getRoles());

			return "admin/index";
	}

	@RequestMapping(path = { "index" }, method = RequestMethod.POST)
	public String indexPost(
			) {
			return "admin/index";
	}

	@RequestMapping(path = { "searchuser" }, method = RequestMethod.GET)
	public String searchUserGet(
			) {
			return "admin/searchuser";
	}

	@RequestMapping(path = { "searchuser" }, method = RequestMethod.POST)
	public String searchUserPost(
			HttpServletRequest request,
			Model model
		) {
			String limitString = request.getParameter("limit");
			String offsetString = request.getParameter("offset");
			int limit,offset;
			if(limitString==null) {
				limit = 5;
			}
			else {
				limit = Integer.parseInt(limitString);
			}
			if(offsetString==null) {
				offset = 0;
			}
			else {
				offset =  Integer.parseInt(offsetString);
			}
			String registration = request.getParameter("registration");
			String login = request.getParameter("login");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");

			int pageId = offset/limit;
			//Pageable pageable = new PageRequest(pageId, limit);

			List<User> userList = this.userCrud.findByRegLogFirstLast(
					registration,login,firstname,lastname
					);

			int count = userList.size();

			int pagePrevId;
			if(pageId==0) {
				pagePrevId = 0;
			}
			else {
				pagePrevId=pageId-1;
			}

			int pageNextId;
			if(pageId*limit<userList.size()) {
				pageNextId = pageId + 1;
			}
			else {
				pageNextId=pageId;
			}

			int prevOffset=offset-limit;
			if(prevOffset<0) {
				prevOffset=0;
			}
			int nextOffset=offset+limit;
			if(nextOffset>count) {
				nextOffset=offset;
			}

			model.addAttribute("userlist", userList);
			model.addAttribute("next",pageNextId);
			model.addAttribute("prev",pagePrevId);
			model.addAttribute("nextOffset",nextOffset);
			model.addAttribute("prevOffset",prevOffset);
			model.addAttribute("limit",""+limit);

			return "admin/searchuser";
	}

	@RequestMapping(path = { "user/list" }, method = RequestMethod.GET)
	public String users(
			@RequestParam("limit") int limit,
			@RequestParam("offset") int offset,
			HttpServletResponse response,
			Model model) {

			int pageId = offset/limit;

			Pageable pageable = new PageRequest(pageId, limit);
			int count = (int) userCrud.count();
			Page<User> userPage = this.userCrud.findAll(pageable);
			List<User> userList = userPage.getContent();

			int pagePrevId;
			if(pageId==0) {
				pagePrevId = 0;
			}
			else {
				pagePrevId=pageId-1;
			}

			int pageNextId;
			if(pageId*limit<userList.size()) {
				pageNextId = pageId + 1;
			}
			else {
				pageNextId=pageId;
			}

			int prevOffset=offset-limit;
			if(prevOffset<0) {
				prevOffset=0;
			}
			int nextOffset=offset+limit;
			if(nextOffset>count) {
				nextOffset=offset;
			}
			model.addAttribute("userlist", userList);
			model.addAttribute("next",pageNextId);
			model.addAttribute("prev",pagePrevId);
			model.addAttribute("nextOffset",nextOffset);
			model.addAttribute("prevOffset",prevOffset);
			model.addAttribute("limit",limit);

		return "admin/userlist";
	}

	@RequestMapping(path = { "user/{idUser}/update" }, method = RequestMethod.GET)
	public String userUpdate(
			@PathVariable("idUser") long idUser,
			Model model
			) {

				 String[] fields = {"firstname","lastname", "login", "registration_cgi"};
				 List<String> fieldList = new ArrayList<String>();
				 for(String field : fields) {
					 fieldList.add(field);
				 }

				 User user = userCrud.findOne(idUser);
				 List<Agency> agencies = agencyCrud.findAll();
				 List<Pole> poles =  poleCrud.findAll();
				 List<SecurityRole> roles =  roleCrud.findAll();
				 //List<SecurityRole> userRoles = roleCrud.findAll();
				 List<SecurityRole> userRoles = roleCrud.findAllByUserId(idUser);
				 System.out.println("nombre de roles total : "+ roles.size());
				 System.out.println("nombre de roles pour l'utilisateur : "+ userRoles.size());

				 Pole userPole = user.getPole();
				 Agency userAgency = user.getAgency();

				 Hashtable<String, String> dictFr=new Hashtable<String, String>();
				 dictFr.put("firstname", "prénom");
				 dictFr.put("lastname", "nom");
				 dictFr.put("login", "login");
				 dictFr.put("registration_cgi", "matricule");

				 model.addAttribute("fieldList",fieldList);
				 model.addAttribute("dictFr",dictFr);
				 model.addAttribute("userItem",user);
				 model.addAttribute("userRoles",userRoles);

				 if(userAgency==null) {
					 model.addAttribute("agencyName","pas d'agence d'attribuée");
				 }
				 else {
					 model.addAttribute("agencyName",userAgency.getName());
				 }
				 if(userPole==null) {
					 model.addAttribute("poleName","pas de pole d'attribuée");
				 }
				 else {
					 model.addAttribute("poleName",userPole.getName());
				 }

				 model.addAttribute("poles",poles);
				 model.addAttribute("agencies",agencies);
				 model.addAttribute("roles",roles);

			return "admin/userupdate";
	}

	@RequestMapping(path = { "user/{idUser}/update" }, method = RequestMethod.POST)
	public String userUpdatePost(
			@PathVariable("idUser") long idUser,
			Model model,
			@RequestParam("login") String login,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("registration") String registration,
			@RequestParam("pole") String poleName,
			@RequestParam("agency") String agencyName,
			@RequestParam("role") String roleName


			) {
			User user = userCrud.findOne(idUser);
			user.setLogin(login);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setRegistration_cgi(registration);

			//SecurityRole role = roleCrud.findByRole(roleName);
			Agency agency = agencyCrud.findByName(agencyName);
			Pole pole = poleCrud.findByName(poleName);


			user.setAgency(agency);
			user.setPole(pole);

			userCrud.save(user);
			System.out.println(""+user.getLogin()+", "+user.getFirstname()+", "+user.getLastname()
					+", pole = " + poleName +", agency = "+  agencyName + ", role="+ roleName);

			return "redirect:/admin2/index";
		//return "redirect:/admin2/user/"+idUser+"/update";
	}


	//@Secured("ROLE_USER")
	@RequestMapping(value = "adminInputNiko" , method = RequestMethod.POST)
	public String adminInputPost( Model model) {
System.err.println("ca marche pas !!");
		//return inputNikoRedirect;
		return "redirect:/root/inputNiko";
	}
}
