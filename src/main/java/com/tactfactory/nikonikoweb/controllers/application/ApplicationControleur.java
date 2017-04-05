package com.tactfactory.nikonikoweb.controllers.application;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;

public class ApplicationControleur {

	public static final String BASE = "root";
	public final static String PATH = "/";

	public final static String REDIRECT = "redirect:";

	public final static String ROUTE_INPUT_NIKO = PATH + "inputNiko";
	public final static String ROUTE_INPUT_NIKO_DATE_SAVE = PATH + "inputDateSave";
	public final static String ROUTE_INPUT_NIKO_TEAM_SELECT = PATH + "inputTeamSelect";
	public final static String ROUTE_INPUT_NIKO_LOGOUT = PATH + "nikoLogout";
	public final static String ROUTE_INPUT_NIKO_CALENDAR = PATH + "nikoCalendar";

	public final static String ROUTE_CALENDAR = PATH + "calendar";
	public final static String ROUTE_CALENDAR_NIKONIKO = PATH + "calendarInputNiko";
	public final static String ROUTE_CALENDAR_LOGOUT = PATH + "calendarLogout";
	public final static String ROUTE_CALENDAR_TEAM_SELECT = PATH + "calendarTeamSelect";
	public final static String ROUTE_CALENDAR_DATE_SUIV = PATH + "calendarDatesuiv";
	public final static String ROUTE_CALENDAR_DATE_PRECED = PATH + "calendarDatepreded";

	public final static String ROUTE_GRAPH_WEEK = "{teamId}" + PATH +"{weekId}" + PATH + "by_team_by_week";
	public final static String ROUTE_GRAPH_WEEK_LOGOUT  = "{teamId}" + PATH +"{weekId}" + PATH  + "graphWeekLogout";
	public final static String ROUTE_GRAPH_WEEK_NIKONIKO = "{teamId}" + PATH +"{weekId}" + PATH  + "graphWeekInputNiko";
	public final static String ROUTE_GRAPH_WEEK_CALENDAR = "{teamId}" + PATH +"{weekId}" + PATH  + "graphWeekCalendar";

	protected String logoutRedirect = REDIRECT + "login?logout";
	protected String graphWeeklogoutRedirect = REDIRECT + PATH + "login?logout";
	protected String inputNikoRedirect = REDIRECT + ROUTE_INPUT_NIKO;
	protected String calendarRedirect = REDIRECT + ROUTE_CALENDAR;
	protected String graphWeekRedirect = REDIRECT + PATH + ROUTE_GRAPH_WEEK;

	protected String inputNikoView = PATH + BASE + PATH + "input";
	protected String calendarView = PATH + BASE + PATH + "calendar";
	protected String resultView = "result" + PATH + "by_team_by_week";

	/* DateTime Formatters */
	/* ------------------- */
	protected DateTimeFormatter javaFormatDateTime = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
	protected DateTimeFormatter sqlFormatDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	protected DateTimeFormatter viewDateTime = DateTimeFormat.forPattern("dd MM YYYY");

	/* Crud Access */
	/* ----------- */
	@Autowired
	protected INikoNikoCrudRepository nikoNikoCrud;

	@Autowired
	protected IUserCrudRepository userCrud;

	@Autowired
	protected IPoleCrudRepository poleCrud;

	@Autowired
	protected ITeamCrudRepository teamCrud;



}
