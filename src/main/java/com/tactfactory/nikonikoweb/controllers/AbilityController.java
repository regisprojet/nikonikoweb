package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Ability;

@Controller
@RequestMapping(AbilityController.BASE_URL)
public class AbilityController extends ViewBaseController<Ability>{

	public final static String BASE_URL = "/ability";

	public AbilityController() {
		super(Ability.class, BASE_URL);
	}
}
