package com.tactfactory.nikonikoweb.controllers.application.view;

import java.util.Date;
import java.util.List;

import javax.management.relation.Role;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.tactfactory.nikonikoweb.controllers.application.ApplicationControleur;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

@Controller
public class InputNikoNikoController extends ApplicationControleur {

	protected DateTime currentDateTime = new DateTime();
	protected Team teamSelect = null;
	protected Long userId = null;

	public String getInputNikoRedirect() {
		return inputNikoRedirect;
	}

	public InputNikoNikoController() {
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.GET)
	public String inputNikoGet(Model model,
			HttpServletRequest request) {

		// find of cookie team name
		// ------------------------
		Cookie cookie = WebUtils.getCookie(request, "userteam");
		if(cookie!=null) {
			if(cookie.getValue() != null) {
				List<Team> teamsNames = (List<Team>) teamCrud.findTeamByName(cookie.getValue());
				if(!teamsNames.isEmpty())
					teamSelect = teamsNames.get(0);
			}
		}

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userCrud.findByLogin(userDetails.getUsername());
		userId = currentUser.getId();

		// find the first team if not already found
		// ----------------------------------------
		List<Team> teams = (List<Team>) teamCrud.findTeamByUserId(userId);
		if(teamSelect == null)
			teamSelect = teams.get(0);

		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("equipes", teams);

		// find all nikoniko from current team
		// -----------------------------------
		DateTime nDate = new DateTime(currentDateTime);
		List<NikoNiko> nikos = (List<NikoNiko>) nikoNikoCrud.findByTeamMonth(teams.get(0).getId(), nDate.getYear(), nDate.getMonthOfYear()) ;

		if(currentUser.getPole()!=null) {
			model.addAttribute("verticale", currentUser.getPole().getName());
		}
		else {
			model.addAttribute("verticale", "verticaleName");
		}

		String nikoComment="";
		int nikoSatisfaction = 0;
		Long nikoId = 0l; //prend la valeur de l'id du nikoniko existant
		Boolean isanonymous = true;
		Date nikoLogDate = new Date();

		List<NikoNiko> nikoNikos = nikoNikoCrud.getNikonikoByUserId(userId);

		if(nikoNikos.isEmpty())
			System.err.println("PAS DE NIKONIKOS ");
		else
			for (NikoNiko nikoNiko : nikoNikos) {
				String nikoDate = viewDateTime.print(new DateTime(nikoNiko.getLog_date()));
				String currentDateStr = viewDateTime.print(currentDateTime);
				if(nikoDate.equals(currentDateStr)) {
					nikoSatisfaction = nikoNiko.getSatisfaction();
					nikoComment = nikoNiko.getComment();
					isanonymous = nikoNiko.getIsAnonymous();
					nikoId = nikoNiko.getId();
					nikoLogDate = nikoNiko.getLog_date();
					break;
				}
			}

		// add comment and satisfaction of existing nikoniko
		// -------------------------------------------------
		model.addAttribute("nikoComment", nikoComment);
		model.addAttribute("nikoSatisfaction", nikoSatisfaction);

		model.addAttribute("isanonymous", isanonymous);

		model.addAttribute("log_date", nikoLogDate);

		model.addAttribute("nikoId", nikoId);

		model.addAttribute("nikos", nikos);

		model.addAttribute("newDayDate",currentDateTime.toDate());

		model.addAttribute("equipes", teams);
		model.addAttribute("equipeSelect", teamSelect.getName());
		model.addAttribute("currentUserRoles", currentUser.getRoles());
		for (SecurityRole role : currentUser.getRoles()) {
			System.err.println(role.getRole());
		}

		return inputNikoView;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, boolean is_anonymous, Long nikoId, Model model) {

		nikoNiko.setUser(userCrud.findOne(userId));
		// insert new nikoniko
		// -------------------
		if((nikoId == 0)&&(nikoNiko.getSatisfaction()>0)) {
			nikoNiko.setIsAnonymous(true);
			nikoNiko.setLog_date(currentDateTime.toDate());
			nikoNikoCrud.save(nikoNiko);

		// update nikoniko
		// ---------------
		} else {
			System.err.println("  EXIST NIKINIKO");
			nikoNiko.setIsAnonymous(is_anonymous);
			nikoNiko.setId(nikoId);
			nikoNiko.setChange_date(currentDateTime.toDate());

			nikoNikoCrud.save(nikoNiko);
		}

		// initialisation de currentDate � la date courante
		// ------------------------------------------------
		/*currentDate = new Date();*/

		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO_DATE_SAVE , method = RequestMethod.POST)
	public String inputNikoPost(Date newDayDate, Model model) {

		currentDateTime = new DateTime(newDayDate);
		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO_TEAM_SELECT , method = RequestMethod.POST)
	public String inputTeamSelectPost(
			HttpServletResponse response,
			@RequestParam ("team") String team, Model model) {

		response.addCookie(new Cookie("userteam", team));
		if(team != null) {
			teamSelect = teamCrud.findTeamByName(team).get(0);
		}
		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO_CALENDAR , method = RequestMethod.POST)
	public String inputNikoRestPost( Model model) {

		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO_LOGOUT , method = RequestMethod.POST)
	public String inputNikoLogoutPost( Model model) {
		userId = null;
		return logoutRedirect;
	}


}