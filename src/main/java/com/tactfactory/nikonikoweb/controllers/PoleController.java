package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Pole;

@Controller
@RequestMapping(PoleController.BASE_URL)
public class PoleController extends ViewBaseController<Pole>{

	public final static String BASE_URL = "/pole";

	public PoleController() {
		super(Pole.class, BASE_URL);
	}
}
