package com.tactfactory.nikonikoweb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@Entity
@Table(name = "nikoniko")
public class NikoNiko extends DatabaseItem {

	@Transient
	public static final String TABLE = "nikoniko";

	@Transient
	public static final String[] FIELDS = { "id", "log_date", "change_date", "satisfaction", "comment",
			"isAnonymous", "user_id", "project_id" };

	@Column(name = "log_date", nullable = false)
	private Date log_date;

	@Column(name = "change_date", nullable = true)
	private Date change_date;

	@Column(name = "satisfaction", nullable = false)
	private Integer satisfaction;

	@Column(name = "nikoniko_comment", nullable = true)
	private String comment;

	@Column(name = "isanonymous", nullable = false)
	private Boolean isAnonymous;

	@ManyToOne
	private User user;

	@ManyToOne
	private Project project;

	/**
	 * @return the log_date
	 */
	public Date getLog_date() {
		if (this.log_date == null) {
			this.log_date = new Date();
		}
		return log_date;
	}

	/**
	 * @param log_date
	 *            the log_date to set
	 */
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}

	/**
	 * @return the change_date
	 */
	public Date getChange_date() {
		return change_date;
	}

	/**
	 * @param change_date
	 *            the change_date to set
	 */
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

	/**
	 * @return the satisfaction
	 */
	public int getSatisfaction() {
		return satisfaction;
	}

	/**
	 * @param satisfaction
	 *            the satisfaction to set
	 */
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = NikoNikoSatisfaction.satisfactionRule(satisfaction);
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the isAnonymous
	 */
	public Boolean getIsAnonymous() {
		if (this.isAnonymous == null) {
			this.isAnonymous = true;
		}
		return isAnonymous;
	}

	/**
	 * @param isAnonymous
	 *            the isAnonymous to set
	 */
	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public NikoNiko(User user, Project project, int satisfaction) {
		this();
		this.user = user;
		this.project = project;
		this.setSatisfaction(satisfaction);
		this.log_date = new Date();
		this.user.getNikonikos().add(this);
		this.project.getNikonikos().add(this);
	}

	public NikoNiko(User user, Project project, int satisfaction, String comment) {
		this(user, project, satisfaction);
		this.comment = comment;
	}

	public NikoNiko(User user, Project project, int satisfaction, Boolean isAnonymous) {
		this(user, project, satisfaction);
		this.isAnonymous = isAnonymous;
	}

	public NikoNiko(User user, Project project, int satisfaction,
			String comment, Boolean isAnonymous) {
		this(user, project, satisfaction);
		this.comment = comment;
		this.isAnonymous = isAnonymous;
	}

	public NikoNiko() {
		super(NikoNiko.TABLE, NikoNiko.FIELDS);
	}

	@Override
	public String toString() {
		return "NikoNiko [log_date=" + log_date + ", change_date="
				+ change_date + ", satisfaction=" + satisfaction + ", comment="
				+ comment + ", isAnonymous=" + isAnonymous + "]";
	}

	private static class NikoNikoSatisfaction {

		public static final int[] satisfactionItems = { 1, 2, 3 };
		public static final int defaultSatisfactionError = 0;

		public static Boolean inSatisfactionItems(int satisfaction) {
			Boolean flag = false;
			for (int i = 0; i < satisfactionItems.length; i++) {
				if (satisfaction == satisfactionItems[i]) {
					flag = true;
					break;
				}
			}
			return flag;
		}

		public static int satisfactionRule(int satisfaction) {
			if (inSatisfactionItems(satisfaction)) {
				return satisfaction;
			} else {
				String error = "Error satisfaction not in ";
				for (int i = 0; i < satisfactionItems.length - 1; i++) {
					error += satisfactionItems[i] + ", ";
				}
				error += satisfactionItems[satisfactionItems.length - 1] + ".";
				System.err.println(error);
				return defaultSatisfactionError;
			}
		}
	}
}
