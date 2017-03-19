package com.tactfactory.nikonikoweb.dao.base;

import org.springframework.data.repository.CrudRepository;

import com.tactfactory.nikonikoweb.models.base.DatabaseItem;

public interface IBaseCrudRepository<T extends DatabaseItem> extends CrudRepository<T, Long> {

}
