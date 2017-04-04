package com.tactfactory.nikonikoweb.controllers.application.view;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.nikonikoweb.controllers.application.ApplicationControleur;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

@Controller
public class CalendarController extends ApplicationControleur {

	protected DateTime currentDateTime = new DateTime();

	protected Team teamSelect = null;

	public CalendarController() {
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR , method = RequestMethod.GET)
	public String calendarGet(Model model,
			HttpServletRequest request) {

		// find of cookie team name
		// ------------------------
		Cookie cookie = WebUtils.getCookie(request, "userteam");
		if(cookie.getValue() != null) {
			List<Team> teamsNames = (List<Team>) teamCrud.findTeamByName(cookie.getValue());
			if(!teamsNames.isEmpty())
				teamSelect = teamsNames.get(0);
		}

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userCrud.findByLogin(userDetails.getUsername());
		Long userId = currentUser.getId();

		// find the first team if not already found
		// ----------------------------------------
		List<Team> teams = (List<Team>) teamCrud.findTeamByUserId(userId);
		if(teamSelect == null)
			teamSelect = teams.get(0);

		// find all nikoniko from current team
		// -----------------------------------
		List<NikoNiko> nikos = (List<NikoNiko>) nikoNikoCrud.findByTeamMonth(teamSelect.getId(), currentDateTime.getYear(), currentDateTime.getMonthOfYear()) ;

		// replace return character by @@ in comment
		// -----------------------------------------
		for (NikoNiko nikoNiko : nikos) {
			nikoNiko.setComment(nikoNiko.getComment().replaceAll("[\r\n]", "@@"));
		}

		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		model.addAttribute("page", "inputNikoNiko");

		model.addAttribute("nikos", nikos);

		model.addAttribute("newDayDate",currentDateTime.toDate());

		String newDayDateStr = sqlFormatDateTime.print(currentDateTime);
		model.addAttribute("newDayDateStr" , newDayDateStr);

		if(currentUser.getPole()!=null) {
			model.addAttribute("verticale", currentUser.getPole().getName());
		} else {
			model.addAttribute("verticale", "verticaleName");
		}
		model.addAttribute("equipes", teams);
		model.addAttribute("equipeSelect", teamSelect.getName());

		return calendarView;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_CALENDAR_DATE_PRECED , method = RequestMethod.POST)
	public String calendarDateprededPost(Date newDayDate, Model model) {

		currentDateTime = new DateTime(newDayDate).minusMonths(1);
		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_CALENDAR_DATE_SUIV , method = RequestMethod.POST)
	public String calendarDatesuivPost(Date newDayDate, Model model) {

		currentDateTime = new DateTime(newDayDate).minusMonths(-1);
		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_CALENDAR_TEAM_SELECT , method = RequestMethod.POST)
	public String calendarTeamSelectPost(
			HttpServletResponse response,
			@RequestParam ("team") String team, Model model) {

		response.addCookie(new Cookie("userteam", team));
		if(team != null)
			teamSelect = teamCrud.findTeamByName(team).get(0);
		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR , method = RequestMethod.POST)
	public String calendarPost(Model model) {

		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_CALENDAR_NIKONIKO , method = RequestMethod.POST)
	public String calendarInputPost( Model model) {

		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR_LOGOUT , method = RequestMethod.POST)
	public String calendarLogoutPost(Model model) {

		return logoutRedirect;
	}



}
