package com.tactfactory.nikonikoweb.controllers.base.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;
import com.tactfactory.nikonikoweb.utils.DumpFields;

public abstract class ViewBaseController<T extends DatabaseItem> extends
		BaseController<T> {

	protected String baseName;
	protected String basePath;

	protected String basePage;

	protected String createView;
	protected String createRedirect;

	protected String deleteView;
	protected String deleteRedirect;

	protected String updateView;
	protected String updateRedirect;

	protected String showView;
	protected String showRedirect;

	protected String listView;
	protected String listRedirect;
	protected String baseView;

	@Autowired
	protected IUserCrudRepository userCrud;

	protected ViewBaseController(Class<T> clazz, String basePath) {
		super(clazz);

		this.baseName = clazz.getSimpleName();
		this.basePath = basePath;
		this.baseView = "base";
		this.basePage = LIST_ACTION;

		this.createView = this.baseView + PATH_CREATE_FILE;
		this.deleteView = this.baseView + PATH_DELETE_FILE;
		this.updateView = this.baseView + PATH_UPDATE_FILE;
		this.showView = this.baseView + PATH_SHOW_FILE;
		this.listView = this.baseView + PATH_LIST_FILE;

		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_LIST;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_LIST;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_LIST;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_LIST;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_LIST;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = ROUTE_LIST, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("page", this.baseName + " " + LIST_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userCrud.findByLogin(userDetails.getUsername());
		model.addAttribute("currentUserRoles", currentUser.getRoles());

		return listView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_SHOW, method = RequestMethod.GET)
	public String itemGet(@PathVariable Long id, Model model) {
		model.addAttribute("page", this.baseName + " " + SHOW_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("currentItem", DumpFields.fielder(super.getItem(id)));
		return showView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_CREATE, method = RequestMethod.GET)
	public String createItemGet(Model model) {
		model.addAttribute("page", this.baseName + " " + CREATE_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute(
				"currentItem",
				DumpFields.fielderAdvance(
						DumpFields.createContentsEmpty(super.getClazz()),
						super.getClazz()));
		model.addAttribute("basePage",basePage);
		return createView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_CREATE, method = RequestMethod.POST)
	public String createItemPost(T item, Model model) {
		try {
			super.insertItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createRedirect;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_UPDATE, method = RequestMethod.GET)
	public String updateItemGet(@PathVariable Long id, Model model) {
		model.addAttribute("page", this.baseName + " " + UPDATE_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("currentItem", DumpFields.fielderAdvance(
				super.getItem(id),
				super.getClazz()));
		return updateView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_UPDATE, method = RequestMethod.POST)
	public String updateItemPost(@ModelAttribute T item) {
		super.updateItem(item);
		return updateRedirect;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_DELETE, method = RequestMethod.GET)
	public String deleteItemGet(@PathVariable Long id, Model model) {
		model.addAttribute("page", this.baseName + " " + DELETE_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("currentItem", DumpFields.fielder(super.getItem(id)));
		return deleteView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = ROUTE_DELETE, method = RequestMethod.POST)
	public String deleteItemPost(@PathVariable Long id) {
		super.deleteItem(id);
		return deleteRedirect;
	}
}
