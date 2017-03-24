package com.tactfactory.nikonikoweb.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Function;

@Controller
@RequestMapping(FunctionViewController.BASE_URL)
public class FunctionViewController extends ViewBaseController<Function>{

	public final static String BASE_URL = "/admin/function";

	public FunctionViewController() {
		super(Function.class, BASE_URL);
	}
}
