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
import com.tactfactory.nikonikoweb.dao.IProjectCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Project;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(ProjectViewController.BASE_URL)
public class ProjectViewController extends ViewBaseController<Project> {

	public final static String BASE_URL = "/admin/project";

	public final static String ROUTE_BASE = "project";
	public final static String ROUTE_REDIRECT = "admin/project";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String teams = "teams";
	protected final static String teamsLinks = "teamslink";

	protected final static String nikonikos = "nikonikos";
	protected final static String nikonikosLinks = "nikonikoslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_TEAMS = PATH_BASE + PATH + associationMultiShow;
	protected final static String PATH_TEAMSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_TEAMSLINKS_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;

	protected final static String PATH_NIKONIKOS =  PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_NIKONIKOSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_NIKONIKOSLINKS_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String PROJECT_ID = "{projectId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_TEAMS = PROJECT_ID + PATH + teams;
	protected final static String ROUTE_TEAMSLINKS = PROJECT_ID + PATH
			+ teamsLinks;

	protected final static String ROUTE_NIKONIKOS = PROJECT_ID + PATH
			+ nikonikos;
	protected final static String ROUTE_NIKONIKOSLINKS = PROJECT_ID + PATH
			+ nikonikosLinks;

	public ProjectViewController() {
		super(Project.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	IProjectCrudRepository projectCrud;

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@RequestMapping(path = ROUTE_INDEX, method = RequestMethod.GET)
	public String projects(Model model) {
		model.addAttribute("page", "All projects");
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

	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams linker");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<Team> teams = (List<Team>) teamCrud.findAll();
		model.addAttribute("items", DumpFields.<Team> listFielder(teams));

		ArrayList<Long> teamsIds = new ArrayList<Long>();
		for (Team team : project.getTeams()) {
			teamsIds.add(team.getId());
		}
		model.addAttribute("linkedItems", teamsIds);

		return PATH_TEAMSLINKS;
	}

	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getTeams().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getTeams().add(teamCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return PATH_TEAMSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_TEAMS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields
				.<Team> listFielder(new ArrayList<Team>(project.getTeams())));
		return PATH_TEAMS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos linker");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<NikoNiko> nikoNikos = nikonikoCrud.findProjectAssociate(projectId);
		nikoNikos.addAll(nikonikoCrud.findWithoutProjectAssociate());
		model.addAttribute("items",
				DumpFields.<NikoNiko> listFielder(nikoNikos));

		ArrayList<Long> nikoNikosIds = new ArrayList<Long>();
		for (NikoNiko nikoNiko : project.getNikonikos()) {
			nikoNikosIds.add(nikoNiko.getId());
		}
		model.addAttribute("linkedItems", nikoNikosIds);

		return PATH_NIKONIKOSLINKS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getNikonikos().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getNikonikos().add(nikonikoCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return PATH_NIKONIKOSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_NIKONIKOS, method = RequestMethod.GET)
	public String getNikoNikosForProject(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields
				.<NikoNiko> listFielder(new ArrayList<NikoNiko>(project
						.getNikonikos())));
		return PATH_NIKONIKOS;
	}
}
