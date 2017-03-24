package com.tactfactory.nikonikoweb.models.security;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@Entity
@Table(name = "security_role")
public class SecurityRole extends DatabaseItem{

	@Transient
	public static final String TABLE = "security_role";

	@Transient
	public static final String[] FIELDS = { "id", "role" };

	private String role;

	@ManyToMany
	@JoinTable(name = "users_securityroles",
    	joinColumns = @JoinColumn(name = "role_id"),
    	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public SecurityRole(String table, String[] fields, String role) {
		super(table, fields);
		this.role = role;
	}

	public SecurityRole() {
		super(TABLE, FIELDS);
	}
}
