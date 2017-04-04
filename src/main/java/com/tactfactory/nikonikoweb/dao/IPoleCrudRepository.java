package com.tactfactory.nikonikoweb.dao;

import java.util.List;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.Pole;
import com.tactfactory.nikonikoweb.models.User;

public interface IPoleCrudRepository extends IBaseCrudRepository<Pole> {
	public List<Pole> findAll();
	public Pole findByName(String name);
}
