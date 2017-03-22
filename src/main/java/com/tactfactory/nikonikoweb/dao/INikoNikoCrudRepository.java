package com.tactfactory.nikonikoweb.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;

public interface INikoNikoCrudRepository extends IBaseCrudRepository<NikoNiko> {

	@Query(value = "INSERT INTO user_nikoniko (User_id,nikonikos_id) values (:userId , :nikoNikoId)" , nativeQuery = true)
	public void insert_user_NikoNiko_relation(@Param("userId") Long userId, @Param("nikoNikoId") Long nikoNikoId);
}
