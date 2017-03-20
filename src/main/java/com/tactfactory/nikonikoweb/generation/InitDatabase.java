package com.tactfactory.nikonikoweb.generation;

import java.util.ArrayList;

import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;

public class InitDatabase {
	ArrayList<Function> functionList;
	ArrayList<Ability> abilityList;
	User userAdmin;
	
	
	public ArrayList<Function> getFunctionList() {
		return functionList;
	}


	public void setFunctionList(ArrayList<Function> functionList) {
		this.functionList = functionList;
	}


	public ArrayList<Ability> getAbilityList() {
		return abilityList;
	}


	public void setAbilityList(ArrayList<Ability> abilityList) {
		this.abilityList = abilityList;
	}


	public User getUserAdmin() {
		return userAdmin;
	}


	public void setUserAdmin(User userAdmin) {
		this.userAdmin = userAdmin;
	}


	public InitDatabase() {
		functionList = new ArrayList<Function>();
		abilityList = new ArrayList<Ability>();
		
		String[] functions = {"administrateur","vip", "développeur", "chef de projet"};
		String[] abilities = {"vue","edition"};
		
		for(String current: functions) {
			Function function = new Function();
			function.setName(current);
			functionList.add(function);			
		}

		for(String current: abilities) {
			Ability ability = new Ability();
			ability.setName(current);
			abilityList.add(ability);			
		}
		userAdmin = new User("admin","admin","admin","admin","0000");
	}
}	
