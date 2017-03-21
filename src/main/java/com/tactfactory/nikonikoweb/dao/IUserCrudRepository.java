package com.tactfactory.nikonikoweb.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.Ability;
import com.tactfactory.nikonikoweb.models.User;

public interface IUserCrudRepository extends IBaseCrudRepository<User>{
	@Query(value="SELECT * FROM user u WHERE u.login = :login",
            nativeQuery = true) 
	public List<User> findByLogin(@Param("login") String login);

	@Query(value="SELECT f.name FROM user_function uf,function f WHERE uf.User_id=:id AND uf.functions_id=f.id",
            nativeQuery = true) 
	public String functionById(@Param("id") long id);

	@Query(value="SELECT a.id FROM user_function uf,function f,ability a,function_ability fa WHERE fa.abilities_id = a.id AND fa.Function_id = f.id AND uf.User_id=:id AND uf.functions_id=f.id",
            nativeQuery = true) 
	public Set<BigInteger> abilitiesById(@Param("id") long id);

}
