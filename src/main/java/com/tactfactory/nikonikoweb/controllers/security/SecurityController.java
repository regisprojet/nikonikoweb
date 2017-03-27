package com.tactfactory.nikonikoweb.controllers.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tactfactory.nikonikoweb.controllers.root.InputNikoNikoController;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityLogin;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

@Controller
@RequestMapping("/")
public class SecurityController {

		@Autowired
		private UserDetailsService userService;

		@Autowired
		private IUserCrudRepository userCrud;

		@RequestMapping(path ="/login", method =  RequestMethod.GET)
		public String loginGet() {
			return "root/login";
		}

		// add by Denis
		@RequestMapping(path = "/login", method =  RequestMethod.POST)
		public String loginPost() {
			// pourquoi cette fonction n'est pas appelée?
			return "root/inputNiko";
		}

		@RequestMapping(path ="/logout", method =  RequestMethod.GET)
		public String logout(HttpServletRequest request, HttpServletResponse response) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null) {
				new SecurityContextLogoutHandler().logout(request,response,auth);
			}
			return "redirect:/login?logout";
		}
		


}
