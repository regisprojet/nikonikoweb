package com.tactfactory.nikonikoweb.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;

public interface INikoNikoCrudRepository extends IBaseCrudRepository<NikoNiko> {
	@Query(value = "SELECT * FROM nikoniko n WHERE n.user_id = :id", nativeQuery = true)
	public Set<NikoNiko> getAllByUserId(@Param("id") long id);

}
