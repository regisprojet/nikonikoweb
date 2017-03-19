package com.tactfactory.nikonikoweb.generation;

import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;

public class Generation {
	public static void main(String[] args) {
		User user = new User();
		Function function = new Function();
		function.setName("administrateur");
	
		System.out.println(function);
	}
}
