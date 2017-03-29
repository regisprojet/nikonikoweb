package com.tactfactory.nikonikoweb.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.models.Greeting;

public interface IGreetingCrudRepository extends CrudRepository<Greeting, Long>{

//		@Query(value="select g from greeting g ORDER BY id DESC LIMIT :offset, :limit" , 
//				nativeQuery=true)
//		public Iterable<Greeting> findAllPagination(
//				@Param ("offset") Long offset,
//				@Param ("limit") Long limit);

	List<Greeting> findAll(Pageable pageable);
	
}
