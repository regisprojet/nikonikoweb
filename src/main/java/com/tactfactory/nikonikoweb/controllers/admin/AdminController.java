package com.tactfactory.nikonikoweb.controllers.admin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.nikonikoweb.dao.IAbilityCrudRepository;
import com.tactfactory.nikonikoweb.dao.IFunctionCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityLogin;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(AdminController.BASE_URL)
public class AdminController {
	
	public final static String BASE_URL = "/admin2";

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IFunctionCrudRepository functionCrud;

	@Autowired
	private IAbilityCrudRepository abilityCrud;
	
	
	@RequestMapping(path = { BASE_URL, "menu" }, method = RequestMethod.GET)
	public String menuGet(Model model) {

		return "admin/menu";
	}

	
	@RequestMapping(path = { BASE_URL, "adduser" }, method = RequestMethod.GET)
	public String adduserGet(Model model) {
		model.addAttribute("page", "ajout utilisateur");
		//User user = DumpFields.createContentsEmpty(User.class);
		String[] fieldsManual =  { "login", "lastname", "firstname" };
		
		model.addAttribute("fields", fieldsManual);
		Map<String, Map<String,Object>> currentItem = DumpFields.fielderAdvance(
				DumpFields.createContentsEmpty(User.class),
				User.class);	
		model.addAttribute("currentItem", currentItem);
		
		Iterable<Function> functions = functionCrud.findAll();
		Set<String> functionNames = new HashSet<String>();
		for(Function function : functions) {
			functionNames.add(function.getName());
		}
	    model.addAttribute("functionNames", functionNames);
	    
		
		return "admin/adduser";
	}
	
	@RequestMapping(path = { BASE_URL, "adduser" }, method = RequestMethod.POST)
	public String adduserPost(@ModelAttribute("userForm") User user, Model model) {
		return "redirect:adduser";
	}

	
	@RequestMapping(path = { "index" }, method = RequestMethod.GET)
	public String index(
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
			Pageable pageable = new PageRequest(pageId, limit);
			
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
			@PathVariable("idUser") long idUser
			) {
			return "admin/userupdate";
	}
	
	
	
}
