package com.tactfactory.nikonikoweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

public interface ITeamCrudRepository extends IBaseCrudRepository<Team> {

	@Query(value = "SELECT * FROM team t WHERE t.name = :name" , nativeQuery = true)
	public List<Team> findTeamByName(@Param("name") String name);


	@Query(value = "SELECT * FROM team t INNER JOIN user_team ut ON t.id = ut.teams_id INNER JOIN user u ON u.id = ut.User_id WHERE u.id = :userId" , nativeQuery = true)
	public List<Team> findTeamByUserId(@Param("userId") Long userId);

	Team findByName(String name);
	List<Team> findAll();
}
