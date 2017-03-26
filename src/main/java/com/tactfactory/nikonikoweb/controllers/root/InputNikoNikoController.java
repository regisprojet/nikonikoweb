package com.tactfactory.nikonikoweb.controllers.root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.IPoleCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;

@Controller
public class InputNikoNikoController {

	public final static String PATH = "/";
	public static final String BASE = "root";
	public final static String ROUTE_INPUT_NIKO = "/inputNiko";
	private String inputNikoView;
	private String inputNikoRedirect;
	private Long userId = 4l;
	Date currentDate = new Date();

	public String getInputNikoRedirect() {
		return inputNikoRedirect;
	}

	SimpleDateFormat sm = new SimpleDateFormat("dd MM YYYY");

	public InputNikoNikoController() {
		this.inputNikoView = PATH + BASE + PATH + "input";
		this.inputNikoRedirect = "redirect:" + ROUTE_INPUT_NIKO;
	}

	@Autowired
	private INikoNikoCrudRepository nikoNikoCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IPoleCrudRepository poleCrud;

	@Autowired
	private INikoNikoCrudRepository nikoCrud;

	@Secured("ROLE_USER")
	@RequestMapping(path = {PATH, ROUTE_INPUT_NIKO} , method = RequestMethod.GET)
	public String inputNikoGet(Model model) {
		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("equipe", "teamName");
		model.addAttribute("verticale", "verticaleName");

		User currentUser =  userCrud.findOne(userId);
		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		String nikoComment="";
		int nikoSatisfaction = 0;
		Long nikoId = 0l; //prend la valeur de l'id du nikoniko existant
		Boolean isanonymous = true;
		Date nikoLogDate = new Date();

		List<NikoNiko> nikoNikos = nikoNikoCrud.getNikonikoByUserId(userId);

		if(nikoNikos.isEmpty())
			System.err.println("PAS DE NIKONIKOS ");
		else
			for (NikoNiko nikoNiko : nikoNikos) {
				String nikoDate = sm.format(nikoNiko.getLog_date());
				String currentDateStr = sm.format(currentDate);
				if(nikoDate.equals(currentDateStr)) {
					nikoSatisfaction = nikoNiko.getSatisfaction();
					nikoComment = nikoNiko.getComment();
					isanonymous = nikoNiko.getIsAnonymous();
					nikoId = nikoNiko.getId();
					nikoLogDate = nikoNiko.getLog_date();
					break;
				}
			}

		// add comment and satisfaction of existing nikoniko
		// -------------------------------------------------
		model.addAttribute("nikoComment", nikoComment);
		model.addAttribute("nikoSatisfaction", nikoSatisfaction);

		model.addAttribute("isanonymous", isanonymous);

		model.addAttribute("log_date", nikoLogDate);

		model.addAttribute("nikoId", nikoId);

		model.addAttribute("newDayDate",currentDate);
		return inputNikoView;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, boolean is_anonymous, Long nikoId, Model model) {

		nikoNiko.setUser(userCrud.findOne(userId));

		// insert new nikoniko
		// -------------------
		if((nikoId == 0)&&(nikoNiko.getSatisfaction()>0)) {
			nikoNiko.setIsAnonymous(true);
			nikoNiko.setLog_date(currentDate);

			nikoNikoCrud.save(nikoNiko);

		// update nikoniko
		// ---------------
		} else {
			System.err.println("  EXIST NIKINIKO");
			nikoNiko.setIsAnonymous(is_anonymous);
			nikoNiko.setId(nikoId);
			nikoNiko.setChange_date(currentDate);

			nikoNikoCrud.save(nikoNiko);
		}

		// initialisation de currentDate à la date courante
		// ------------------------------------------------
		/*currentDate = new Date();*/

		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/inputDateSave" , method = RequestMethod.POST)
	public String inputNikoPost(String newDayDate, Model model) {

		System.err.println("  envoi newDayDate= " + newDayDate);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		try {
			date = formatter.parse(newDayDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentDate = date;
		return inputNikoRedirect;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/quit" , method = RequestMethod.POST)
	public String inputNikoLogoutPost( Model model) {

		System.err.println("  Coucou envoi" );

		return "login";
	}

}