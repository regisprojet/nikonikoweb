package com.tactfactory.nikonikoweb.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@Entity
@Table(name = "team")
public class Team extends DatabaseItem {

	@Transient
	public static final String TABLE = "team";

	@Transient
	public static final String[] FIELDS = { "id", "name", "serial" };

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String serial;

	@ManyToMany
	private Set<Project> projects;

	@ManyToMany
	private Set<User> users;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @param serial
	 *            the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Team(String name, String serial) {
		super(Team.TABLE, Team.FIELDS);
		this.name = name;
		this.serial = serial;
	}

	public Team() {
		super(Team.TABLE, Team.FIELDS);
	}
}
