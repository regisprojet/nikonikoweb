package com.tactfactory.nikonikoweb.controllers.root;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;

@Controller
public class CalendarController {

	public final static String PATH = "/";
	public static final String BASE = "root";
	public final static String ROUTE_CALENDAR = "/calendar";
	private String calendarView;
	private String calendarRedirect;
	private String nikonikoRedirect;
	private DateTime currentDate = new DateTime();
	//DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

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

	@Autowired
	private ITeamCrudRepository teamCrud;

	@Secured("ROLE_USER")
	@RequestMapping(path = ROUTE_CALENDAR , method = RequestMethod.GET)
	public String calendarGet(Model model) {

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userCrud.findByLogin(userDetails.getUsername());
		Long userId = currentUser.getId();

		// find the first team if not already found
		// ----------------------------------------
		List<Team> teams = (List<Team>) teamCrud.findTeamByUserId(userId);

		// find all nikoniko from current team
		// -----------------------------------
		//DateTime nDate = new DateTime(currentDate);
		//DateTime nDate = new DateTime();
		List<NikoNiko> nikos = (List<NikoNiko>) nikoNikoCrud.findByTeamMonth(teams.get(0).getId(), currentDate.getYear(), currentDate.getMonthOfYear()) ;

		// replace return character by @@ in comment
		// -----------------------------------------
		for (NikoNiko nikoNiko : nikos) {
			nikoNiko.setComment(nikoNiko.getComment().replaceAll("[\r\n]", "@@"));
		}

		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("equipe", "teamName");

		model.addAttribute("nikos", nikos);

		model.addAttribute("newDayDate",currentDate.toDate());
		//model.addAttribute("newDayDateStr",fmt.print(currentDate));

		//String newDayDateStr = currentDate.toDateTimeISO().toString();
		String newDayDateStr = currentDate.getYear() + "-" + currentDate.getMonthOfYear() + "-" + currentDate.getDayOfMonth() + " " +
				currentDate.getHourOfDay() + ":" + currentDate.getMinuteOfHour() + ":" + currentDate.getSecondOfMinute();
		model.addAttribute("newDayDateStr" , newDayDateStr);

/*System.err.println("  --->  " + newDayDateStr);
for (NikoNiko nikoNiko : nikos) {
	System.err.println("      --->  " + nikoNiko.getSatisfaction() );
}*/

		if(currentUser.getPole()!=null) {
			model.addAttribute("verticale", currentUser.getPole().getName());
		} else {
			model.addAttribute("verticale", "verticaleName");
		}
		return calendarView;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/calendarDatepreded" , method = RequestMethod.POST)
	public String calendarDateprededPost(Date newDayDate, Model model) {

		currentDate = new DateTime(newDayDate).minusMonths(1);
		//currentDate = new Date(nDate);
		return calendarRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/calendarDatesuiv" , method = RequestMethod.POST)
	public String calendarDatesuivPost(Date newDayDate, Model model) {

		currentDate = new DateTime(newDayDate).minusMonths(-1);
		return calendarRedirect;
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
