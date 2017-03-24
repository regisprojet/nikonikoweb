package com.tactfactory.nikonikoweb.controllers.root;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.environment.Environment;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Pole;
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
	private IUserCrudRepository userCrud;

	@Autowired
	private IPoleCrudRepository poleCrud;
	
	@Autowired
	private INikoNikoCrudRepository nikoCrud;
	
	@Secured("user")
	@RequestMapping(value =  ROUTE_INPUT_NIKO , method = RequestMethod.GET)
	public String inputNikoGet(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		if(currentUser==null) {
			return "redirect:/login";
		}
		
		
		Set<NikoNiko> nikonikos = nikoCrud.getAllByUserId(currentUser.getId());
		Date today = new Date();
		for(NikoNiko nikoniko : nikonikos) {
			Date date1 = nikoniko.getLog_date();
			Date date2 = today;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date1String = sdf.format(date1);
			String date2String = sdf.format(date2);
			if(date1String.equals(date2String)) {
				System.out.println("Vous avez déjà voté aujourd'hui");
			}
		}
		
		BigInteger bigId = userCrud.poleIdById(currentUser.getId());
		if(bigId!=null) {
			Pole pole = poleCrud.findOne(bigId.longValue());
			model.addAttribute("verticale", pole.getName());
		}
		else {
			model.addAttribute("verticale", "verticaleName");
		}
			
		model.addAttribute("page", "inputNikoNiko");
		model.addAttribute("equipe", "teamName");

		//User currentUser =  userCrud.findOne(userId);
		model.addAttribute("nomUser", currentUser.getLastname());
		model.addAttribute("prenomUser", currentUser.getFirstname());

		Set<BigInteger> nikosId = userCrud.getNikoNikoById(userId);
		Date currentDate = new Date();

		for (BigInteger nikoId : nikosId) {
			//NikoNiko niko =
			//System.err.println("-- > niko " + niko.getLog_date().toString() + "  currentDate= " + currentDate);
		}

		return inputNikoView;
	}

	@RequestMapping(value = ROUTE_INPUT_NIKO , method = RequestMethod.POST)
	public String inputNikoPost(@ModelAttribute NikoNiko nikoNiko, Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		if(currentUser==null) {
			return "redirect:/login";
		}
		
		nikoNiko.setIsAnonymous(true);
		nikoNiko.setUser(currentUser); // a besoin de @OneToMany(fetch = FetchType.EAGER) sur nikonikos
		nikoNiko.setLog_date(new Date());
	
		nikoNiko = nikoCrud.save(nikoNiko);
		currentUser.getNikonikos().add(nikoNiko);
		userCrud.save(currentUser);
	
		return "redirect:/voteOk";
	}

	@RequestMapping(value =  "/voteOk" , method = RequestMethod.GET)
	public String voteOk(Model model) {
		Environment environment = Environment.getInstance();
		User currentUser = environment.getCurrentUser();
		if(currentUser==null) {
			return "redirect:/login";
		}
		
	    return "root/voteOk";
	}
	
}