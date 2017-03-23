package com.tactfactory.nikonikoweb.controllers.root;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.tactfactory.nikonikoweb.utils.DumpFields;

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
	Date currentDate = new Date();
	SimpleDateFormat sm = new SimpleDateFormat("dd MM YYYY");

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

		/*if(currentDate.equals(""))
			currentDate = sm.format(new Date());*/

		String nikoComment="";
		int nikoSatisfaction = 0;
		Long nikoId = 0l; //prend la valeur de l'id du nikoniko existant
		Boolean isanonymous = true;
		Date nikoLogDate = new Date();

		//NikoNiko currentNikoniko = new NikoNiko();
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
		//System.err.println("  envoi isanonimous= " + isanonymous);

		model.addAttribute("log_date", nikoLogDate);
		//System.err.println("  envoi log_date= " + nikoLogDate);

		model.addAttribute("nikoId", nikoId);

		model.addAttribute("newDayDate",currentDate);
		return inputNikoView;
	}

	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, boolean is_anonymous, Long nikoId, Model model) {

		nikoNiko.setUser(userCrud.findOne(userId));

		// insert new nikoniko
		// -------------------
		if((nikoId == 0)&&(nikoNiko.getSatisfaction()>0)) {
			nikoNiko.setIsAnonymous(true);
			//nikoNiko.setLog_date(new Date());
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

}