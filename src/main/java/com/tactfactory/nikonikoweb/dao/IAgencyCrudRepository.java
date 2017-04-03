package com.tactfactory.nikonikoweb.dao;

import java.util.List;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.Agency;

public interface IAgencyCrudRepository extends IBaseCrudRepository<Agency> {

	public List<Agency> findAll();
}
