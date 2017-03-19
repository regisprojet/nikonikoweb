package com.tactfactory.nikonikoweb.models.security;
/**
 * 
 * @author regis
 * l'instance de cette classe est utilisée comme argument à la méthode loginPost pour
 * récupérer le login et le mot de passe
 */
public class SecurityLogin {
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
