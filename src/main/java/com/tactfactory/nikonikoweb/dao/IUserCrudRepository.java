package com.tactfactory.nikonikoweb.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tactfactory.nikonikoweb.dao.base.IBaseCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.User;

public interface IUserCrudRepository extends IBaseCrudRepository<User>{
	@Query(value = "SELECT * FROM user u WHERE u.login = :login", nativeQuery = true)
	public List<User> findByLogin(@Param("login") String login);

	@Query(value = "SELECT f.name FROM user_function uf,function f WHERE uf.User_id=:id AND uf.functions_id=f.id", nativeQuery = true)
	public String functionById(@Param("id") long id);

	@Query(value = "SELECT f.id FROM user_function uf,function f WHERE uf.User_id=:id AND uf.functions_id=f.id", nativeQuery = true)
	public Set<BigInteger> functionIdsById(@Param("id") long id);

	@Query(value = "SELECT a.id FROM user_function uf,function f,ability a,function_ability fa WHERE fa.abilities_id = a.id AND fa.Function_id = f.id AND uf.User_id=:id AND uf.functions_id=f.id",
			nativeQuery = true)
	public Set<BigInteger> abilitiesById(@Param("id") long id);

	@Query(value = "SELECT pole_id FROM user u WHERE u.id=:id", nativeQuery = true)
	public BigInteger poleIdById(@Param("id") long id);

	@Query(value = "SELECT nikonikos_id from user_nikoniko INNER JOIN user ON user_nikoniko.User_id = user.id WHERE user.id = :ident",
			nativeQuery = true)
	public Set<BigInteger> getUser_NikoNikobyId(@Param("ident") long ident);
}
