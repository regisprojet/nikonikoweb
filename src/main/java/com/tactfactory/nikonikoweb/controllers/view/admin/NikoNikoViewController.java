package com.tactfactory.nikonikoweb.controllers.view.admin;

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
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Project;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(NikoNikoViewController.BASE_URL)
public class NikoNikoViewController extends ViewBaseController<NikoNiko>{

	public final static String BASE_URL = "/admin/nikoniko";

	public final static String ROUTE_BASE = "nikoniko";
	public final static String ROUTE_REDIRECT = "admin/nikoniko";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String user = "user";
	protected final static String userLink = "userlink";

	protected final static String project = "project";
	protected final static String projectLink = "projectlink";

	protected final static String associationMultiShow = "associationMonoShow";
	protected final static String associationMultiEdit = "associationMonoEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_USER = PATH_BASE + PATH + associationMultiShow;
	protected final static String PATH_USERLINK = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_USERLINK_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;

	protected final static String PATH_PROJECT =  PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_PROJECTLINK = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_PROJECTLINK_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String NIKONIKO_ID = "{nikonikoId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_USER = NIKONIKO_ID + PATH + user;
	protected final static String ROUTE_USERLINK = NIKONIKO_ID + PATH
			+ userLink;

	protected final static String ROUTE_PROJECT = NIKONIKO_ID + PATH
			+ project;
	protected final static String ROUTE_PROJECTLINK = NIKONIKO_ID + PATH
			+ projectLink;

	public NikoNikoViewController() {
		super(NikoNiko.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@Autowired
	IUserCrudRepository userCrud;

	@Autowired
	IProjectCrudRepository projectCrud;

	@RequestMapping(ROUTE_INDEX)
	public String projects(Model model) {
		model.addAttribute("page", "All nikonikos");
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

	@RequestMapping(path = ROUTE_USERLINK, method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long nikonikoId) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		model.addAttribute("page", nikoniko.getLog_date() + " user linker");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(nikoniko));

		List<User> users = (List<User>) userCrud.findAll();
		model.addAttribute("items", DumpFields.<User> listFielder(users));

		if (nikoniko.getUser() != null) {
			model.addAttribute("linkedItem", nikoniko.getUser().getId());
		}

		return PATH_USERLINK;
	}

	@RequestMapping(path = ROUTE_USERLINK, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long nikonikoId,
			@RequestParam(value = "idLinked") Long idLinked) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		nikoniko.setUser(userCrud.findOne(idLinked));

		nikonikoCrud.save(nikoniko);

		return PATH_USERLINK_REDIRECT;
	}

	@RequestMapping(path = ROUTE_USER, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long nikonikoId) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		model.addAttribute("page", nikoniko.getLog_date() + " user");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(nikoniko));

		if (nikoniko.getUser() != null) {
			model.addAttribute("linkedItem", DumpFields
					.fielder(nikoniko.getUser()));
		}

		return PATH_USER;
	}

	@RequestMapping(path = ROUTE_PROJECTLINK, method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model,
			@PathVariable Long nikonikoId) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		model.addAttribute("page", nikoniko.getLog_date() + " project linker");
		model.addAttribute("fields", Project.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(nikoniko));

		List<Project> projects = (List<Project>) projectCrud.findAll();
		model.addAttribute("items", DumpFields.<Project> listFielder(projects));

		if (nikoniko.getProject() != null) {
			model.addAttribute("linkedItem", nikoniko.getProject().getId());
		}

		return PATH_PROJECTLINK;
	}

	@RequestMapping(path = ROUTE_PROJECTLINK, method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model,
			@PathVariable Long nikonikoId,
			@RequestParam(value = "idLinked") Long idLinked) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		nikoniko.setProject(projectCrud.findOne(idLinked));

		nikonikoCrud.save(nikoniko);

		return PATH_PROJECTLINK_REDIRECT;
	}

	@RequestMapping(path = ROUTE_PROJECT, method = RequestMethod.GET)
	public String getNikoNikosForProject(Model model,
			@PathVariable Long nikonikoId) {
		NikoNiko nikoniko = super.getItem(nikonikoId);

		model.addAttribute("page", nikoniko.getLog_date() + " user");
		model.addAttribute("fields", Project.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(nikoniko));

		if (nikoniko.getProject() != null) {
			model.addAttribute("linkedItem", DumpFields
					.fielder(nikoniko.getProject()));
		}

		return PATH_PROJECT;
	}
}
