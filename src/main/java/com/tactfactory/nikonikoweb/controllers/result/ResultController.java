package com.tactfactory.nikonikoweb.controllers.result;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tactfactory.nikonikoweb.controllers.root.RootController;
import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;

@Controller
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private INikoNikoCrudRepository nikoCrud;

	@Autowired
	private ITeamCrudRepository teamCrud;

	@Secured(value={"ROLE_ADMIN","ROLE_USER", "ROLE_VIP", "ROLE_PROJECTLEADER"})
	@RequestMapping(value = { "{teamId}/{weekId}/by_team_by_week" }, method = RequestMethod.GET)
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
		Iterable<NikoNiko> nikos = nikoCrud.findByTeam(teamId);
		ArrayList<NikoNiko> nikosByWeek = new ArrayList<NikoNiko>();
		
		ArrayList<Integer> greensWeek = new ArrayList<Integer> ();
		ArrayList<Integer> yellowsWeek = new ArrayList<Integer> ();
		ArrayList<Integer>  redsWeek = new ArrayList<Integer> ();
	
		for(int i=0;i<7;i++) {
			greensWeek.add(0);
			yellowsWeek.add(0);
			redsWeek.add(0);
		}
	
		GregorianCalendar todayCalendar =new GregorianCalendar();
		Date todayDate = new Date();
		todayCalendar.setTime(todayDate);
		int today = (todayCalendar.get(todayCalendar.DAY_OF_WEEK)+5)%7;
		for(NikoNiko niko : nikos) {
			GregorianCalendar calendar =new GregorianCalendar();
		    Date nikoDate = niko.getLog_date();
			calendar.setTime(nikoDate);
			int dayNiko = (calendar.get(calendar.DAY_OF_WEEK)+5)%7;
			System.out.println("Dayniko ="+dayNiko);
			long diff = (todayDate.getTime() - nikoDate.getTime())/ (1000 * 60 * 60 * 24);
			
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
		
		
		return "result/by_team_by_week";
		
	}

}
