package com.tactfactory.nikonikoweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;

public interface INikoNikoCrudRepository extends IBaseCrudRepository<NikoNiko> {

	@Query(value = "SELECT * FROM nikoniko n WHERE n.user_id = :userId", nativeQuery = true)
	public List<NikoNiko> getNikonikoByUserId(@Param("userId") Long userId);
}
