package com.tactfactory.nikonikoweb.controllers.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;

@Controller
public class inputNikoNikoController {
//public class inputNikoNikoController extends BaseController<T>{

	public final static String PATH = "/";
	public static final String BASE = "root";
	//public final static String ROUTE_INPUT_NIKO = "{userId}/inputNiko";
	public final static String ROUTE_INPUT_NIKO = "/inputNiko";
	public final static String ROUTE_OUTPUT_NIKO = "{nikoSatisfaction}/inputNiko";
	private String inputNikoView;
	private String inputNikoRedirect;
	private Long userId = 21l;

	public inputNikoNikoController() {
		this.inputNikoView = PATH + BASE + PATH + "input";
	}

	@Autowired
	private IBaseCrudRepository<NikoNiko> nikoNikoCrud;

	@Autowired
	private IBaseCrudRepository<User> userCrud;

	@RequestMapping(value =  ROUTE_INPUT_NIKO , method = RequestMethod.GET)
	public String inputNikoGet(Model model) {
		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("verticale", "verticaleName");
		model.addAttribute("equipe", "teamName");

		User currentUser =  userCrud.findOne(userId);
		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());
		
		// find if NikoNiko already voted
		

		return inputNikoView;
	}

	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, Model model) {

		User currentUser =  userCrud.findOne(userId);
		System.err.println(currentUser.getFirstname() + " " + currentUser.getLastname() + " ("
				+ nikoNiko.getSatisfaction() + ") [" + nikoNiko.getComment() + "]");
		return inputNikoView;
	}
}