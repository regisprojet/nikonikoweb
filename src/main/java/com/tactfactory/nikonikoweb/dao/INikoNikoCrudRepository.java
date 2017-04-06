package com.tactfactory.nikonikoweb.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;

public interface INikoNikoCrudRepository extends IBaseCrudRepository<NikoNiko> {
	@Query(value = "SELECT * FROM nikoniko n WHERE n.user_id = :id", nativeQuery = true)
	public List<NikoNiko> getAllByUserId(@Param("id") long id);

	@Query(value = "SELECT * FROM nikoniko INNER JOIN user_nikoniko ON nikoniko.id = user_nikoniko.nikonikos_id WHERE user_nikoniko.User_id = ?1", nativeQuery = true)
	public List<NikoNiko> findUserAssociate(Long projectId);

	@Query(value = "SELECT * FROM nikoniko WHERE nikoniko.id NOT IN(SELECT nikoNikos_id FROM user_nikoniko)", nativeQuery = true)
	public List<NikoNiko> findWithoutUserAssociate();

	@Query(value = "SELECT * FROM nikoniko INNER JOIN project_nikoniko ON nikoniko.id = project_nikoniko.nikoNikos_id WHERE project_nikoniko.Project_id = ?1", nativeQuery = true)
	public List<NikoNiko> findProjectAssociate(Long projectId);

	@Query(value = "SELECT * FROM nikoniko WHERE nikoniko.id NOT IN(SELECT nikoNikos_id FROM project_nikoniko)", nativeQuery = true)
	public List<NikoNiko> findWithoutProjectAssociate();

	@Query(value = "SELECT * FROM nikoniko n WHERE n.user_id = :userId", nativeQuery = true)
	public List<NikoNiko> getNikonikoByUserId(@Param("userId") Long userId);

	@Query(value = "SELECT * FROM nikoniko n INNER JOIN user u ON n.user_id=u.id INNER JOIN user_team ut ON u.id=ut.User_id where ut.teams_id=:teamId", nativeQuery=true)
	public Iterable<NikoNiko> findByTeam(@Param("teamId") Long teamId);

	@Query(value = "SELECT * FROM nikoniko n INNER JOIN user u ON n.user_id=u.id INNER JOIN user_team ut ON u.id=ut.User_id where ut.teams_id=:teamId AND YEAR(n.log_date) = :currentYear AND MONTH(n.log_date) = :currentMonth ORDER BY u.id", nativeQuery=true)
	public List<NikoNiko> findByTeamMonth(@Param("teamId") Long teamId, @Param("currentYear") int currentYear, @Param("currentMonth") int currentMonth);

	@Query(value = "SELECT MAX(id) FROM nikoniko", nativeQuery=true)
	//@Query(value = "SELECT id FROM nikoniko", nativeQuery=true)
	public Long findMaxId();
}
