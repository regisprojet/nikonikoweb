package com.tactfactory.nikonikoweb.controllers;

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
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController extends ViewBaseController<Project> {

	public final static String BASE_URL = "/project";

	public ProjectController() {
		super(Project.class, BASE_URL);
	}

	@Autowired
	IProjectCrudRepository projectCrud;

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@RequestMapping("index")
	public String projects(Model model) {
		model.addAttribute("page", "All projects");
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		return "project/index";
	}

	@RequestMapping(path="{projectId}/teamslink", method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams linker");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<Team> teams = (List<Team>) teamCrud.findAll();
		model.addAttribute("items", DumpFields.<Team>listFielder(teams));

		ArrayList<Long> teamsIds = new ArrayList<Long>();
		for (Team team : project.getTeams()) {
			teamsIds.add(team.getId());
		}
		model.addAttribute("linkedItems", teamsIds);

		return "project/teamslink";
	}

	@RequestMapping(path="{projectId}/teamslink", method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model, @PathVariable Long projectId, @RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getTeams().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getTeams().add(teamCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return "redirect:/project/index";
	}

	@RequestMapping("{projectId}/teams")
	public String getTeamsForProject(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields.<Team>listFielder(new ArrayList<Team>(project.getTeams())));
		return "project/teams";
	}

	@RequestMapping(path="{projectId}/nikonikoslink", method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos linker");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<NikoNiko> nikoNikos = (List<NikoNiko>) nikonikoCrud.findAll();
		model.addAttribute("items", DumpFields.<NikoNiko>listFielder(nikoNikos));

		ArrayList<Long> nikoNikosIds = new ArrayList<Long>();
		for (NikoNiko nikoNiko : project.getNikoNikos()) {
			nikoNikosIds.add(nikoNiko.getId());
		}
		model.addAttribute("linkedItems", nikoNikosIds);

		return "project/nikonikoslink";
	}

	@RequestMapping(path="{projectId}/nikonikoslink", method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model, @PathVariable Long projectId, @RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getNikoNikos().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getNikoNikos().add(nikonikoCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return "redirect:/project/index";
	}

	@RequestMapping("{projectId}/nikonikos")
	public String getNikoNikosForProject(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields.<NikoNiko>listFielder(new ArrayList<NikoNiko>(project.getNikoNikos())));
		return "project/nikonikos";
	}
}
