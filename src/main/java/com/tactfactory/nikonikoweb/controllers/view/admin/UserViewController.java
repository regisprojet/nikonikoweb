package com.tactfactory.nikonikoweb.controllers.view.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.ISecurityRoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(UserViewController.BASE_URL)
public class UserViewController extends ViewBaseController<User> {

	public final static String BASE_URL = "/admin/user";

	public final static String ROUTE_BASE = "user";
	public final static String ROUTE_REDIRECT = "admin/user";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String teams = "teams";
	protected final static String teamsLinks = "teamslink";

	protected final static String nikonikos = "nikonikos";
	protected final static String nikonikosLinks = "nikonikoslink";

	protected final static String securityroles = "roles";
	protected final static String securityrolesLinks = "roleslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_TEAMS = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_TEAMSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_TEAMSLINKS_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;

	protected final static String PATH_NIKONIKOS = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_NIKONIKOSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_NIKONIKOSLINKS_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String PATH_SECURITYROLES = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_SECURITYROLESLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_SECURITYROLESLINKS_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String USER_ID = "{userId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_TEAMS = USER_ID + PATH + teams;
	protected final static String ROUTE_TEAMSLINKS = USER_ID + PATH
			+ teamsLinks;

	protected final static String ROUTE_NIKONIKOS = USER_ID + PATH
			+ nikonikos;
	protected final static String ROUTE_NIKONIKOSLINKS = USER_ID + PATH
			+ nikonikosLinks;

	protected final static String ROUTE_SECURITYROLES = USER_ID + PATH
			+ securityroles;
	protected final static String ROUTE_SECURITYROLESLINKS = USER_ID + PATH
			+ securityrolesLinks;

	public UserViewController() {
		super(User.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	IUserCrudRepository userCrud;

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@Autowired
	ISecurityRoleCrudRepository securityRoleCrud;
	
	@Autowired
	IPoleCrudRepository poleCrud;

	//@Secured("ROLE_PROJECTLEADER")
	@RequestMapping(path = ROUTE_INDEX, method = RequestMethod.GET)
	public String users(Model model) {
		model.addAttribute("page", "All users");
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		model.addAttribute(
				"currentItem",
				DumpFields.fielderAdvance(
						DumpFields.createContentsEmpty(super.getClazz()),
						super.getClazz()));
		return PATH_INDEX;
	}

	//@Secured("ROLE_PROJECTLEADER")
	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.GET)
	public String setTeamsForUserGet(Model model, @PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname()
						+ " teams linker");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));

		List<Team> teams = (List<Team>) teamCrud.findAll();
		model.addAttribute("items", DumpFields.<Team> listFielder(teams));

		ArrayList<Long> teamsIds = new ArrayList<Long>();
		for (Team team : user.getTeams()) {
			teamsIds.add(team.getId());
		}
		model.addAttribute("linkedItems", teamsIds);

		return PATH_TEAMSLINKS;
	}

	//@Secured("ROLE_PROJECTLEADER")
	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.POST)
	public String setTeamsForUserPost(Model model,
			@PathVariable Long userId,
			@RequestParam(value = "ids[]") Long[] ids) {
		User user = super.getItem(userId);

		user.getTeams().clear();

		for (Long id : ids) {
			if (id != 0) {
				user.getTeams().add(teamCrud.findOne(id));
			}
		}

		userCrud.save(user);

		return PATH_TEAMSLINKS_REDIRECT;
	}

	//@Secured("ROLE_PROJECTLEADER")
	@RequestMapping(path = ROUTE_TEAMS, method = RequestMethod.GET)
	public String getTeamsForUser(Model model, @PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " teams");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("items", DumpFields
				.<Team> listFielder(new ArrayList<Team>(user.getTeams())));
		return PATH_TEAMS;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.GET)
	public String setNikoNikosForUserGet(Model model,
			@PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname()
						+ " nikonikos linker");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));

		List<NikoNiko> nikoNikos = (List<NikoNiko>) nikonikoCrud.findUserAssociate(userId);
		nikoNikos.addAll(nikonikoCrud.findWithoutUserAssociate());
		model.addAttribute("items",
				DumpFields.<NikoNiko> listFielder(nikoNikos));

		ArrayList<Long> nikoNikosIds = new ArrayList<Long>();
		for (NikoNiko nikoNiko : user.getNikonikos()) {
			nikoNikosIds.add(nikoNiko.getId());
		}
		model.addAttribute("linkedItems", nikoNikosIds);

		return PATH_NIKONIKOSLINKS;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.POST)
	public String setNikoNikosForUserPost(Model model,
			@PathVariable Long userId,
			@RequestParam(value = "ids[]") Long[] ids) {
		User user = super.getItem(userId);

		user.getNikonikos().clear();

		for (Long id : ids) {
			if (id != 0) {
				user.getNikonikos().add(nikonikoCrud.findOne(id));
			}
		}

		userCrud.save(user);

		return PATH_NIKONIKOSLINKS_REDIRECT;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_NIKONIKOS, method = RequestMethod.GET)
	public String getNikoNikosForUser(Model model,
			@PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " nikonikos");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("items", DumpFields
				.<NikoNiko> listFielder(new ArrayList<NikoNiko>(user
						.getNikonikos())));
		return PATH_NIKONIKOS;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_SECURITYROLESLINKS, method = RequestMethod.GET)
	public String setSecurityRolesForUserGet(Model model, @PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname()
						+ " teams linker");
		model.addAttribute("fields", SecurityRole.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));

		List<SecurityRole> securityRoles = (List<SecurityRole>) securityRoleCrud.findAll();
		model.addAttribute("items", DumpFields.<SecurityRole> listFielder(securityRoles));

		ArrayList<Long> securityRolesIds = new ArrayList<Long>();
		for (SecurityRole securityRole : user.getRoles()) {
			securityRolesIds.add(securityRole.getId());
		}
		model.addAttribute("linkedItems", securityRolesIds);

		return PATH_SECURITYROLESLINKS;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_SECURITYROLESLINKS, method = RequestMethod.POST)
	public String setSecurityRolesForUserPost(Model model,
			@PathVariable Long userId,
			@RequestParam(value = "ids[]") Long[] ids) {
		User user = super.getItem(userId);

		user.getRoles().clear();

		for (Long id : ids) {
			if (id != 0) {
				user.getRoles().add(securityRoleCrud.findOne(id));
			}
		}

		userCrud.save(user);

		return PATH_SECURITYROLESLINKS_REDIRECT;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_SECURITYROLES, method = RequestMethod.GET)
	public String getSecurityRolesForUser(Model model, @PathVariable Long userId) {
		User user = super.getItem(userId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " security roles");
		model.addAttribute("fields", SecurityRole.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("items", DumpFields
				.<SecurityRole> listFielder(new ArrayList<SecurityRole>(user.getRoles())));
		return PATH_SECURITYROLES;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = {"{userId}/polelink"}, method = RequestMethod.GET)
	public String getPoleForUser(Model model, @PathVariable Long userId) {
		User user = super.getItem(userId);
		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " security pole");
		model.addAttribute("fields", Pole.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("linkedItem", DumpFields.fielder(user.getPole()));
		
		List<Pole> poles = (List<Pole>) poleCrud.findAll();
		model.addAttribute("items", DumpFields.<Pole> listFielder(poles));
		
		return "base/associationMonoShow";
	}
}
