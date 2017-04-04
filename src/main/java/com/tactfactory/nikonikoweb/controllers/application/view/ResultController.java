package com.tactfactory.nikonikoweb.controllers.application.view;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.application.ApplicationControleur;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;

@Controller
@RequestMapping("/result")
public class ResultController extends ApplicationControleur {

	protected DateTime currentDateTime = new DateTime();

	@Secured(value={"ROLE_ADMIN","ROLE_USER", "ROLE_VIP", "ROLE_PROJECTLEADER"})
	@RequestMapping(value = { ROUTE_GRAPH_WEEK }, method = RequestMethod.GET)
	public String multifunctionGet(Model model,
			@PathVariable Long teamId,
			@PathVariable Long weekId
			) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userCrud.findByLogin(userDetails.getUsername());

		model.addAttribute("username", user.getFirstname()
				+ " " + user.getLastname().toUpperCase());

		Team team = teamCrud.findOne(teamId);
		model.addAttribute("teamname", team.getName());
		Iterable<NikoNiko> nikos = nikoNikoCrud.findByTeam(teamId);
		ArrayList<NikoNiko> nikosByWeek = new ArrayList<NikoNiko>();

		ArrayList<Integer> greensWeek = new ArrayList<Integer> ();
		ArrayList<Integer> yellowsWeek = new ArrayList<Integer> ();
		ArrayList<Integer>  redsWeek = new ArrayList<Integer> ();

		for(int i=0;i<7;i++) {
			greensWeek.add(0);
			yellowsWeek.add(0);
			redsWeek.add(0);
		}

		//GregorianCalendar todayCalendar =new GregorianCalendar();
		//DateTime todayCalendar = new DateTime();
		//Date todayDate = new Date();
		//todayCalendar.setTime(todayDate);
		//DateTime todayDate =

		int today = (currentDateTime.getDayOfWeek() + 5)%7;

		//int today = (todayCalendar.get(todayCalendar.DAY_OF_WEEK)+5)%7;
		for(NikoNiko niko : nikos) {
			//GregorianCalendar calendar =new GregorianCalendar();
			//DateTime calendar = new DateTime();
		    //Date nikoDate1 = niko.getLog_date();
		    DateTime nikoDate = new DateTime(niko.getLog_date());

			//calendar.setTime(nikoDate);
		    DateTime calendar = new DateTime(nikoDate);
		    int dayNiko = (calendar.getDayOfWeek() + 5)%7;
			//int dayNiko = (calendar.get(calendar.DAY_OF_WEEK)+5)%7;

		    System.out.println("Dayniko ="+dayNiko);
			//long diff1 = (todayDate.getTime() - nikoDate1.getTime())/ (1000 * 60 * 60 * 24);
			long diff = (currentDateTime.getMillis() - nikoDate.getMillis())/ (1000 * 60 * 60 * 24);


			if((diff<=today-7.0*weekId) /*&& (diff>=today-7.0*(weekId+1)*/) {
				if(niko.getSatisfaction()==1) {
					greensWeek.set(dayNiko, greensWeek.get(dayNiko)+1);
				}
				else if(niko.getSatisfaction()==2) {
					yellowsWeek.set(dayNiko, yellowsWeek.get(dayNiko)+1);
				}
				else if(niko.getSatisfaction()==3) {
					redsWeek.set(dayNiko, redsWeek.get(dayNiko)+1);
				}
			}
		}
		List greens = greensWeek;
		List yellows = yellowsWeek;
		List reds = redsWeek;

		model.addAttribute("greens",greens);
		model.addAttribute("yellows",yellows);
		model.addAttribute("reds",reds);
		model.addAttribute("equipe",team.getName());
		model.addAttribute("verticale","verticale");


		return resultView;
	}


	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_GRAPH_WEEK_NIKONIKO , method = RequestMethod.POST)
	public String graphWeekInputPost( Model model) {

		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_GRAPH_WEEK_CALENDAR , method = RequestMethod.POST)
	public String graphWeekRestPost( Model model) {

		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_GRAPH_WEEK_LOGOUT , method = RequestMethod.POST)
	public String graphWeekLogoutPost( Model model) {

		return graphWeeklogoutRedirect;
	}
}
