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
import com.tactfactory.nikonikoweb.dao.ISecurityRoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(SecurityRoleViewController.BASE_URL)
public class SecurityRoleViewController extends ViewBaseController<SecurityRole>{
	public final static String BASE_URL = "/admin/securityrole";

	public final static String ROUTE_BASE = "securityrole";
	public final static String ROUTE_REDIRECT = "admin/securityrole";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String users = "users";
	protected final static String usersLinks = "userslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_USERS = PATH_BASE + PATH + associationMultiShow;
	protected final static String PATH_USERSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_USERSLINKS_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;

	protected final static String PROJECT_ID = "{securityRoleId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_USERS = PROJECT_ID + PATH + users;
	protected final static String ROUTE_USERSLINKS = PROJECT_ID + PATH
			+ usersLinks;

	public SecurityRoleViewController() {
		super(SecurityRole.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	ISecurityRoleCrudRepository securityRoleCrud;

	@Autowired
	IUserCrudRepository userCrud;

	//@Secured("ROLE_ADMIN")
	@RequestMapping(ROUTE_INDEX)
	public String projects(Model model) {
		model.addAttribute("page", "All roles");
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

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long securityRoleId) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		model.addAttribute("page", securityRole.getRole() + " users linker");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(securityRole));

		List<User> users = (List<User>) userCrud.findAll();
		model.addAttribute("items", DumpFields.<User> listFielder(users));

		ArrayList<Long> usersIds = new ArrayList<Long>();
		for (User user : securityRole.getUsers()) {
			usersIds.add(user.getId());
		}
		model.addAttribute("linkedItems", usersIds);

		return PATH_USERSLINKS;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long securityRoleId,
			@RequestParam(value = "ids[]") Long[] ids) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		securityRole.getUsers().clear();

		for (Long id : ids) {
			if (id != 0) {
				securityRole.getUsers().add(userCrud.findOne(id));
			}
		}

		securityRoleCrud.save(securityRole);

		return PATH_USERSLINKS_REDIRECT;
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_USERS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long securityRoleId) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		model.addAttribute("page", securityRole.getRole() + " roles");
		model.addAttribute("fields", User.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(securityRole));
		model.addAttribute("items", DumpFields
				.<User> listFielder(new ArrayList<User>(securityRole.getUsers())));
		return PATH_USERS;
	}
}
