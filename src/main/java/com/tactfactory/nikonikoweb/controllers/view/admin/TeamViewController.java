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
import com.tactfactory.nikonikoweb.dao.IProjectCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Project;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(TeamViewController.BASE_URL)
public class TeamViewController extends ViewBaseController<Team>{

	public final static String BASE_URL = "/admin/team";

	public final static String ROUTE_BASE = "team";
	public final static String ROUTE_REDIRECT = "admin/team";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String users = "users";
	protected final static String usersLinks = "userslink";

	protected final static String projects = "projects";
	protected final static String projectsLinks = "projectslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_USERS = PATH_BASE + PATH + associationMultiShow;
	protected final static String PATH_USERSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_USERSLINKS_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;

	protected final static String PATH_PROJECTS =  PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_PROJECTSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_PROJECTSLINKS_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String PROJECT_ID = "{teamId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_USERS = PROJECT_ID + PATH + users;
	protected final static String ROUTE_USERSLINKS = PROJECT_ID + PATH
			+ usersLinks;

	protected final static String ROUTE_PROJECTS = PROJECT_ID + PATH
			+ projects;
	protected final static String ROUTE_PROJECTSLINKS = PROJECT_ID + PATH
			+ projectsLinks;

	public TeamViewController() {
		super(Team.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	IUserCrudRepository userCrud;

	@Autowired
	IProjectCrudRepository projectCrud;

	@RequestMapping(ROUTE_INDEX)
	public String projects(Model model) {
		model.addAttribute("page", "All teams");
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

	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long teamId) {
		Team team = super.getItem(teamId);

		model.addAttribute("page", team.getName() + " teams linker");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(team));

		List<User> users = (List<User>) userCrud.findAll();
		model.addAttribute("items", DumpFields.<User> listFielder(users));

		ArrayList<Long> usersIds = new ArrayList<Long>();
		for (User user : team.getUsers()) {
			usersIds.add(user.getId());
		}
		model.addAttribute("linkedItems", usersIds);

		return PATH_USERSLINKS;
	}

	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long teamId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Team team = super.getItem(teamId);

		team.getUsers().clear();

		for (Long id : ids) {
			if (id != 0) {
				team.getUsers().add(userCrud.findOne(id));
			}
		}

		teamCrud.save(team);

		return PATH_USERSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_USERS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long teamId) {
		Team team = super.getItem(teamId);

		model.addAttribute("page", team.getName() + " teams");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(team));
		model.addAttribute("items", DumpFields
				.<User> listFielder(new ArrayList<User>(team.getUsers())));
		return PATH_USERS;
	}

	@RequestMapping(path = ROUTE_PROJECTSLINKS, method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model,
			@PathVariable Long teamId) {
		Team team = super.getItem(teamId);

		model.addAttribute("page", team.getName() + " nikonikos linker");
		model.addAttribute("fields", Project.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(team));

		List<Project> projects = (List<Project>) projectCrud.findAll();
		model.addAttribute("items",
				DumpFields.<Project> listFielder(projects));

		ArrayList<Long> projectsIds = new ArrayList<Long>();
		for (Project project : team.getProjects()) {
			projectsIds.add(project.getId());
		}
		model.addAttribute("linkedItems", projectsIds);

		return PATH_PROJECTSLINKS;
	}

	@RequestMapping(path = ROUTE_PROJECTSLINKS, method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model,
			@PathVariable Long teamId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Team team = super.getItem(teamId);

		team.getProjects().clear();

		for (Long id : ids) {
			if (id != 0) {
				team.getProjects().add(projectCrud.findOne(id));
			}
		}

		teamCrud.save(team);

		return PATH_PROJECTSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_PROJECTS, method = RequestMethod.GET)
	public String getNikoNikosForProject(Model model,
			@PathVariable Long teamId) {
		Team team = super.getItem(teamId);

		model.addAttribute("page", team.getName() + " nikonikos");
		model.addAttribute("fields", Project.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(team));
		model.addAttribute("items", DumpFields
				.<Project> listFielder(new ArrayList<Project>(team
						.getProjects())));
		return PATH_PROJECTS;
	}
}
