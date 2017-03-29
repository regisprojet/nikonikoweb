package com.tactfactory.nikonikoweb.controllers.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.User;

@Controller
public class CalendarController {

	public final static String PATH = "/";
	public static final String BASE = "root";
	public final static String ROUTE_CALENDAR = "/calendar";
	private String calendarView;
	private String calendarRedirect;
	private String nikonikoRedirect;

	public CalendarController() {
		this.calendarView = PATH + BASE + PATH + "calendar";
		this.calendarRedirect = "redirect:" + ROUTE_CALENDAR;
		this.nikonikoRedirect = "redirect:" + "inputNiko";
	}

	@Autowired
	private INikoNikoCrudRepository nikoNikoCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IPoleCrudRepository poleCrud;

	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR , method = RequestMethod.GET)
	public String calendarGet(Model model) {

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("equipe", "teamName");

		if(currentUser.getPole()!=null) {
			model.addAttribute("verticale", currentUser.getPole().getName());
		} else {
			model.addAttribute("verticale", "verticaleName");
		}
		return calendarView;
	}


	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR , method = RequestMethod.POST)
	public String calendarPost(Model model) {

		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/inputNiko2" , method = RequestMethod.POST)
	public String calendarInputPost( Model model) {

		return nikonikoRedirect;
	}

}
