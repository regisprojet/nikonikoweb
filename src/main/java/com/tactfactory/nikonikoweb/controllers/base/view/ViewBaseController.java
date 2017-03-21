package com.tactfactory.nikonikoweb.controllers.base.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;
import com.tactfactory.nikonikoweb.utils.DumpFields;

public abstract class ViewBaseController<T extends DatabaseItem> extends
		BaseController<T> {

	private String baseName;

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

	protected ViewBaseController(Class<T> clazz, String baseName) {
		super(clazz);

		this.baseName = baseName;
		this.baseView = "base";

		this.createView = this.baseView + PATH_CREATE_FILE;
		this.deleteView = this.baseView + PATH_DELETE_FILE;
		this.updateView = this.baseView + PATH_UPDATE_FILE;
		this.showView = this.baseView + PATH_SHOW_FILE;
		this.listView = this.baseView + PATH_LIST_FILE;

		this.createRedirect = REDIRECT + this.baseName + PATH + ROUTE_LIST;
		this.deleteRedirect = REDIRECT + this.baseName + PATH + ROUTE_LIST;
		this.updateRedirect = REDIRECT + this.baseName + PATH + ROUTE_LIST;
		this.showRedirect = REDIRECT + this.baseName + PATH + ROUTE_LIST;
		this.listRedirect = REDIRECT + this.baseName + PATH + ROUTE_LIST;
	}

	@RequestMapping(value = { PATH, ROUTE_LIST }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("page", this.baseName + " " + LIST_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		return listView;
	}

	@RequestMapping(path = ROUTE_SHOW, method = RequestMethod.GET)
	public String itemGet(@PathVariable Long id, Model model) {
		model.addAttribute("page", this.baseName + " " + SHOW_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("currentItem", DumpFields.fielder(super.getItem(id)));
		return showView;
	}

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
		return createView;
	}

	@RequestMapping(path = ROUTE_CREATE, method = RequestMethod.POST)
	public String createItemPost(T item, Model model) {
		try {
			super.insertItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createRedirect;
	}

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

	@RequestMapping(path = ROUTE_UPDATE, method = RequestMethod.POST)
	public String updateItemPost(@ModelAttribute T item) {
		super.updateItem(item);
		return updateRedirect;
	}

	@RequestMapping(path = ROUTE_DELETE, method = RequestMethod.GET)
	public String deleteItemGet(@PathVariable Long id, Model model) {
		model.addAttribute("page", this.baseName + " " + DELETE_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("currentItem", DumpFields.fielder(super.getItem(id)));
		return deleteView;
	}

	@RequestMapping(path = ROUTE_DELETE, method = RequestMethod.POST)
	public String deleteItemPost(@PathVariable Long id) {
		super.deleteItem(id);
		return deleteRedirect;
	}
}
