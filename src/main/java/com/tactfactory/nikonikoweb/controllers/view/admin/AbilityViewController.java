package com.tactfactory.nikonikoweb.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Ability;

@Controller
@RequestMapping(AbilityViewController.BASE_URL)
public class AbilityViewController extends ViewBaseController<Ability>{

	public final static String BASE_URL = "/admin/ability";

	public AbilityViewController() {
		super(Ability.class, BASE_URL);
	}
}
