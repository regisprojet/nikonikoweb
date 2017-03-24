package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Agency;

@Controller
@RequestMapping(AgencyController.BASE_URL)
public class AgencyController extends ViewBaseController<Agency>{

	public final static String BASE_URL = "/agency";

	public AgencyController() {
		super(Agency.class, BASE_URL);
	}
}