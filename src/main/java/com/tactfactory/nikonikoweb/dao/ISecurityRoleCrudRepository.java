package com.tactfactory.nikonikoweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

public interface ISecurityRoleCrudRepository extends IBaseCrudRepository<SecurityRole>{
	List<SecurityRole> findAll();
	
	SecurityRole findByRole(String role);
	
	@Query(value = "SELECT * FROM security_role sr INNER JOIN users_securityroles usr ON sr.id = usr.role_id WHERE usr.user_id=:userId",
			nativeQuery = true)
	List<SecurityRole> findAllByUserId(@Param("userId") long userId);
}
