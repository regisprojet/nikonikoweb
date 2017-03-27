package com.tactfactory.nikonikoweb.controllers.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;

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

		@RequestMapping(path ="/logout", method =  RequestMethod.GET)
		public String logout(HttpServletRequest request, HttpServletResponse response) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null) {
				new SecurityContextLogoutHandler().logout(request,response,auth);
			}
			return "redirect:/login?logout";
		}
}
