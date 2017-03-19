package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Team;

@Controller
@RequestMapping(TeamController.BASE_URL)
public class TeamController extends ViewBaseController<Team>{

	public final static String BASE_URL = "/team";

	public TeamController() {
		super(Team.class, BASE_URL);
	}
}
