package com.tactfactory.nikonikoweb.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.models.security.SecurityRole;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	private IUserCrudRepository userCrud;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		// renvoyé automatiquement dans le post
		User user = userCrud.findByLogin(login);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		if(user.getEnable()) {
			for(SecurityRole role : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(), grantedAuthorities );
	}
}
