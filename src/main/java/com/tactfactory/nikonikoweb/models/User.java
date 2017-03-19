package com.tactfactory.nikonikoweb.models;

import java.security.InvalidParameterException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.security.SecurityUser;

@Entity
@Table(name = "user")
public class User extends SecurityUser {

	@Transient
    public static final char SEX_MALE = 'M';

	@Transient
    public static final char SEX_FEMALE = 'F';

	@Transient
    public static final char SEX_UNDEFINNED = 'U';

    @Transient
	public static final String TABLE = "user";

    @Transient
	public static final String[] FIELDS = { "id", "login", "password", "sex", "lastname", "firstname",
			"registration_cgi" };

    @Column(nullable = false)
	private String lastname;

    @Column(nullable = false)
	private String firstname;

    @Column(nullable = false)
	private String registration_cgi;

	@OneToMany
	private Set<NikoNiko> nikonikos;

	@ManyToMany
	private Set<Team> teams;

	private Character sex;

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the sex
	 */
	public Character getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(Character sex) {
	    switch (sex) {
	    case User.SEX_MALE:
	    case User.SEX_FEMALE:
	    case User.SEX_UNDEFINNED:
	        this.sex = sex;
	        break;
        default:
            throw new InvalidParameterException();
	    }
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the registration_cgi
	 */
	public String getRegistration_cgi() {
		return registration_cgi;
	}

	/**
	 * @param registration_cgi
	 *            the registration_cgi to set
	 */
	public void setRegistration_cgi(String registration_cgi) {
		this.registration_cgi = registration_cgi;
	}

	/**
	 * @return the nikoNikos
	 */
	public Set<NikoNiko> getNikoNikos() {
		return nikonikos;
	}

	/**
	 * @param nikoNikos
	 *            the nikoNikos to set
	 */
	public void setNikoNikos(Set<NikoNiko> nikoNikos) {
		this.nikonikos = nikoNikos;
	}

	/**
	 * @return the teams
	 */
	public Set<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public User(String login, String password, String lastname, String firstname, String registration_cgi) {
		super(User.TABLE, User.FIELDS, login, password);
		this.lastname = lastname;
		this.firstname = firstname;
		this.registration_cgi = registration_cgi;
	}

	public User() {
		super(User.TABLE, User.FIELDS);
	}

	public User(String firstname, String lastname) {
		this(firstname, lastname, 'I');
	}

	public User(String firstname, String lastname, char sex) {
		super(User.TABLE, User.FIELDS);
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
	}
}
