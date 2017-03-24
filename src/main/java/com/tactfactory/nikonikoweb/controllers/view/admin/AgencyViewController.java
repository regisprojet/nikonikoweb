package com.tactfactory.nikonikoweb.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Agency;

@Controller
@RequestMapping(AgencyViewController.BASE_URL)
public class AgencyViewController extends ViewBaseController<Agency>{

	public final static String BASE_URL = "/admin/agency";

	public AgencyViewController() {
		super(Agency.class, BASE_URL);
	}
}