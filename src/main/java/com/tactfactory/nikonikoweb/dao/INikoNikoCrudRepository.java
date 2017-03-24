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

	@Query(value = "SELECT * FROM nikoniko INNER JOIN user_nikoniko ON nikoniko.id = user_nikoniko.nikonikos_id WHERE user_nikoniko.User_id = ?1", nativeQuery = true)
	List<NikoNiko> findUserAssociate(Long projectId);

	@Query(value = "SELECT * FROM nikoniko WHERE nikoniko.id NOT IN(SELECT nikoNikos_id FROM user_nikoniko)", nativeQuery = true)
	List<NikoNiko> findWithoutUserAssociate();

	@Query(value = "SELECT * FROM nikoniko INNER JOIN project_nikoniko ON nikoniko.id = project_nikoniko.nikoNikos_id WHERE project_nikoniko.Project_id = ?1", nativeQuery = true)
	List<NikoNiko> findProjectAssociate(Long projectId);

	@Query(value = "SELECT * FROM nikoniko WHERE nikoniko.id NOT IN(SELECT nikoNikos_id FROM project_nikoniko)", nativeQuery = true)
	List<NikoNiko> findWithoutProjectAssociate();
}
