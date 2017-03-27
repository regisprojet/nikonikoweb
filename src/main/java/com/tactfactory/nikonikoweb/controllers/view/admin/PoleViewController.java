package com.tactfactory.nikonikoweb.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Pole;

@Controller
@RequestMapping(PoleViewController.BASE_URL)
public class PoleViewController extends ViewBaseController<Pole>{

	public final static String BASE_URL = "/admin/pole";

	
	
	
	public PoleViewController() {
		super(Pole.class, BASE_URL);
	}
}
