package com.tactfactory.nikonikoweb.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

@Entity
@Table(name = "function")
public class Function extends DatabaseItem {
	
	@Transient
	public static final String TABLE = "function";

	@Transient
	public static final String[] FIELDS = { "id", "name"};
	
	@Column(nullable = false)
	private String name;

	@ManyToMany
	private Set<Ability> abilities;
	
	public Set<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(Set<Ability> abilities) {
		this.abilities = abilities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Function() {
		super(Function.TABLE, Function.FIELDS);
		this.abilities = new HashSet<Ability>();
	}
}
