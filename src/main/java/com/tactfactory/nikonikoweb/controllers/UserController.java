package com.tactfactory.nikonikoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.User;

@Controller
@RequestMapping(UserController.BASE_URL)
public class UserController extends ViewBaseController<User>{

	public final static String BASE_URL = "/user";

	public UserController() {
		super(User.class, BASE_URL);
	}
}
