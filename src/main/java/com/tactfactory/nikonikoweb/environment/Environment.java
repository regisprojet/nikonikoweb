package com.tactfactory.nikonikoweb.environment;

import java.util.HashSet;
import java.util.Set;

import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.Function;
import com.tactfactory.nikonikoweb.models.User;

public class Environment {
    /** Récupère l'instance unique de la class Environment.
    * Remarque : le constructeur est rendu inaccessible
    */
	
	private User currentUser;
	private Set<Ability> allAbilities;
	private Set<Function> allFunctions;
	private String abilities;
	private String functions;
	
	
    public static Environment getInstance() {
        if (null == instance) { 
            instance = new Environment();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode <link
    */
    private Environment() {
    	this.allAbilities = new HashSet<Ability>();
    }

    public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Set<Ability> getAllAbilities() {
		return allAbilities;
	}

	public void setAllAbilities(Set<Ability> allAbilities) {
		this.allAbilities = allAbilities;
	}

	public String getAbilities() {
		return abilities;
	}

	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}

	public Set<Function> getAllFunctions() {
		return allFunctions;
	}

	public void setAllFunctions(Set<Function> allFunctions) {
		this.allFunctions = allFunctions;
	}

	public String getFunctions() {
		return functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	/** L'instance statique */
    private static Environment instance;
}
