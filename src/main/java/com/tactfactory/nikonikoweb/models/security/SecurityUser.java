package com.tactfactory.nikonikoweb.models.security;

import java.util.Set;

import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@MappedSuperclass
@Inheritance
public abstract class SecurityUser extends DatabaseItem {

	private String login;
	private String password;
	private Boolean enable;

	@ManyToMany
	@JoinTable(name = "users_securityroles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<SecurityRole> roles;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the roles
	 */
	public Set<SecurityRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<SecurityRole> roles) {
		this.roles = roles;
	}

	public SecurityUser(String table, String[] fields, String login, String password) {
		super(table, fields);
		this.login = login;
		this.password = password;
	}

	public SecurityUser(String table, String[] fields) {
		super(table, fields);
	}
}
