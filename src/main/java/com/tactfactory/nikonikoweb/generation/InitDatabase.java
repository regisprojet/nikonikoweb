package com.tactfactory.nikonikoweb.generation;

import java.util.ArrayList;

import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;

public class InitDatabase {
	ArrayList<Function> functionList;
	ArrayList<Ability> abilityList;
	ArrayList<User> adminList;
	ArrayList<User> devList;
	
	public ArrayList<User> getDevList() {
		return devList;
	}


	public void setDevList(ArrayList<User> devList) {
		this.devList = devList;
	}


	public ArrayList<User> getAdminList() {
		return adminList;
	}


	public void setAdminList(ArrayList<User> admins) {
		this.adminList = admins;
	}


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



	public InitDatabase() {
		functionList = new ArrayList<Function>();
		abilityList = new ArrayList<Ability>();
		adminList = new ArrayList<User>();
		devList = new ArrayList<User>();
		
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
		
		adminList.add(new User("admin","admin","admin","admin","0000"));
		adminList.add(new User("admin","1234","admin2","admin2","0001"));

		//devList.add(new User("regisp","password","regis","ph","0001"));
		//devList.add(new User("denisp","password","denis","pa","0002"));
	}
}	
