package com.tactfactory.nikonikoweb.environment;

import com.tactfactory.nikonikoweb.models.User;

public class Environment {
    /** Récupère l'instance unique de la class Environment.
    * Remarque : le constructeur est rendu inaccessible
    */
	
	private User currentUser;
	
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
    }

    public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/** L'instance statique */
    private static Environment instance;
}
