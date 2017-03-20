package com.tactfactory.nikonikoweb.controllers.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
public class inputNikoNikoController {
//public class inputNikoNikoController extends BaseController<T>{

	public final static String PATH = "/";
	public static final String BASE = "root";
	public final static String ROUTE_INPUT_NIKO = "/inputNiko";
	private String inputNikoView;
	private String inputNikoRedirect;
	private Long userId = 1l;

	public inputNikoNikoController() {
		this.inputNikoView = PATH + BASE + PATH + "input";
	}

	@Autowired
	private IBaseCrudRepository<NikoNiko> NikoNikoCrud;

	@Autowired
	private IBaseCrudRepository<User> UserCrud;

	@RequestMapping(value =  ROUTE_INPUT_NIKO , method = RequestMethod.GET)
	public String inputNikoGet(Model model) {
		model.addAttribute("page", "inputNikoNiko");
		//model.addAttribute("currentUser", DumpFields.fielder(super.getItem(userId)));

		model.addAttribute("satisfaction", 0);
		return inputNikoView;
	}
}