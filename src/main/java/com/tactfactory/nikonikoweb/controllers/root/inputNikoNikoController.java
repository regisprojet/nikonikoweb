package com.tactfactory.nikonikoweb.controllers.root;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
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
		this.inputNikoRedirect = "redirect:" + ROUTE_INPUT_NIKO;
	}

	@Autowired
	private INikoNikoCrudRepository nikoNikoCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@RequestMapping(value =  ROUTE_INPUT_NIKO , method = RequestMethod.GET)
	public String inputNikoGet(Model model) {
		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("verticale", "verticaleName");
		model.addAttribute("equipe", "teamName");

		User currentUser =  userCrud.findOne(userId);
		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		SimpleDateFormat sm = new SimpleDateFormat("YYYY-MM-dd");
		String currentDate = sm.format(new Date());

		Set<BigInteger> nikoNikoIds = userCrud.getUser_NikoNikobyId(userId);
		String nikoComment="";
		int nikoSatisfaction = -1;
		Long nikoId = 0l; //prend la valeur de l'id du nikoniko existant
		boolean isanonymous = true;

		// find if it's a create or update vote
		// ------------------------------------
		for (BigInteger nikoNikoId : nikoNikoIds) {
			//System.err.println("nikoNikoId=" + nikoNikoId + "  (" + nikoNikoId.longValue() + ")");
			NikoNiko nikoniko = new NikoNiko();
			nikoniko = nikoNikoCrud.findOne(nikoNikoId.longValue());

			String nikoDate = sm.format(nikoniko.getLog_date());
			if(nikoDate.equals(currentDate)) {
				nikoSatisfaction = nikoniko.getSatisfaction();
				nikoComment = nikoniko.getComment();
				nikoId = nikoNikoId.longValue();
				isanonymous = nikoniko.getIsAnonymous();

				break;
			}
		}

		// add comment and satisfaction of existing nikoniko
		// -------------------------------------------------
		model.addAttribute("nikoComment", nikoComment);
		model.addAttribute("nikoSatisfaction", nikoSatisfaction);
		model.addAttribute("nikoId", nikoId);
		model.addAttribute("isanonymous", isanonymous);
		return inputNikoView;
	}

	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, Long nikoId, Model model) {

		User currentUser =  userCrud.findOne(userId);

		if(nikoId == 0) {
			nikoNiko.setIsAnonymous(true);
			nikoNiko.setLog_date(new Date());

			// insertion du nouveau nikoniko
			// -----------------------------
			NikoNiko newNiko = nikoNikoCrud.save(nikoNiko);

			// insertion de la relation user_nikoniko
			// --------------------------------------
//			nikoNikoCrud.insert_user_NikoNiko_relation(userId.longValue(), newNiko.getId().longValue());


		} else {
System.err.println(currentUser.getFirstname() + " " + currentUser.getLastname()+ " " + nikoNiko.getLog_date().toString() + " ("
	+ nikoNiko.getSatisfaction() + ") [" + nikoNiko.getComment() + "] " + nikoNiko.getIsAnonymous() +" " + nikoId);

			nikoNiko.setId(nikoId);
			nikoNiko.setChange_date(new Date());

			nikoNikoCrud.save(nikoNiko);
		}


		return inputNikoRedirect;
	}
}