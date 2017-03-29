package com.tactfactory.nikonikoweb.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.tactfactory.nikonikoweb.models.demo.Greeting;

public interface IGreetingCrudRepository extends CrudRepository<Greeting, Long>{
	/*@Query(value = "SELECT * FROM demo_greeting g ORDER BY id DESC LIMIT  :offset, :limit",
			nativeQuery = true)
	public List<Greeting> findAll(@Param("offset") long id, @Param("limit") int limit);*/

	public List<Greeting> findAll(Pageable pageable);

}
