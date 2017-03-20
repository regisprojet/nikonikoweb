package com.tactfactory.nikonikoweb.generation;

import java.util.ArrayList;

import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Agency;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.User;

public class InitDatabase {
	ArrayList<Function> functionList;
	ArrayList<Ability> abilityList;
	ArrayList<User> adminList;
	ArrayList<User> devList;
	ArrayList<Pole> poleList;
	ArrayList<Agency> agencyList;
	
	
	public ArrayList<Agency> getAgencyList() {
		return agencyList;
	}


	public void setAgencyList(ArrayList<Agency> agencyList) {
		this.agencyList = agencyList;
	}


	public ArrayList<Pole> getPoleList() {
		return poleList;
	}


	public void setPoleList(ArrayList<Pole> poleList) {
		this.poleList = poleList;
	}


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
		poleList = new ArrayList<Pole>();
		agencyList = new ArrayList<Agency>();
		
		Function functionAdmin = null;
		Function functionDev = null;
		
		String[] functions = {"administrateur","vip", "developpeur", "chef de projet"};
		String[] abilities = {"vue","edition"};
		String[] poles = {"Telco-Energy)","Agro Tech","CRM-BigData","Testing" };
		String[] agencies = {"Paris","Rennes","Brest","Nantes","Bordeau", "Toulouse","Marseille", 
				"Nice", "Monpellier", "Lyon",  "Grenoble", "Stasbourd", "Lille"};
		
		
		for(String current: functions) {
			Function function = new Function();
			function.setName(current);
			functionList.add(function);
			if(current.equals("developpeur")) {
				functionDev = function;
			}
			else if (current.equals("administrateur")) {
				functionAdmin = function;
			}
		}

		for(String current: abilities) {
			Ability ability = new Ability();
			ability.setName(current);
			abilityList.add(ability);			
		}
		
		for(String current : poles) {
			Pole pole = new Pole();
			pole.setName(current);
			poleList.add(pole);
		}
		
		for(String current : agencies) {
			Agency agency = new Agency();
			agency.setName(current);
			agencyList.add(agency);
		}
		
		User user;
		user = new User("admin","admin","admin","admin","0000");
		user.getFunctions().add(functionAdmin);
		
		adminList.add(user);
		
		user = new User("admin1","1234","admin2","admin2","0001");
		user.getFunctions().add(functionAdmin);
		adminList.add(user);

		user = new  User("regis","toto","regis","ph","0002");
		user.getFunctions().add(functionDev);
		user.setPole(poleList.get(0));
		user.setAgency(agencyList.get(1));
		devList.add(user);
		
		user = new  User("denis","toto","denis","pa","0003");
		user.getFunctions().add(functionDev);
		user.setPole(poleList.get(1));
		user.setAgency(agencyList.get(1));
		devList.add(user);
		
	}
}	
