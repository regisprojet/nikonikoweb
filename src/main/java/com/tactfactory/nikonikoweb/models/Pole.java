package com.tactfactory.nikonikoweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@Entity
@Table(name = "pole")
public class Pole extends DatabaseItem {
	
	@Transient
	public static final String TABLE = "pole";

	@Transient
	public static final String[] FIELDS = { "id", "name"};
	
	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
