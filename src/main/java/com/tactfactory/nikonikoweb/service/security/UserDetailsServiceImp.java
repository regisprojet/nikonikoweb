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

	// renvoyé automatiquement dans le post
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

		User user = userCrud.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if(user.getEnable()) {
			for(SecurityRole role : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
		}
		org.springframework.security.core.userdetails.User userDetails =
				new org.springframework.security.core.userdetails.User(
						user.getLogin(),
						user.getPassword(),
						grantedAuthorities );

		/*user.getLogin(), user.getPassword(), enabled, accountNonExpired,
        credentialsNonExpired, accountNonLocked, grantedAuthorities
        );*/

		return userDetails;
	}
}
