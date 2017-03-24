package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.Function;

@Controller
@RequestMapping(FunctionController.BASE_URL)
public class FunctionController extends ViewBaseController<Function>{

	public final static String BASE_URL = "/function";

	public FunctionController() {
		super(Function.class, BASE_URL);
	}
}
