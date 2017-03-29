package com.tactfactory.nikonikoweb.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		http
     			.authorizeRequests()
				.anyRequest()
				.authenticated()
			.and()
			    .formLogin()
			    	.loginPage("/login")
			    	.usernameParameter("username")
			    	.passwordParameter("password")
			    	.defaultSuccessUrl("/redirect",true)
			    	.permitAll() // sinon il faudrait déjà être authentifié
			.and()			
		            .logout()                                    
                    .permitAll()
		
//		http
//		.authorizeRequests()
//		.anyRequest()
//		.authenticated()
//	.and()
//		.formLogin()
//			.loginPage("/login")
//			.usernameParameter("username").passwordParameter("password")
//			.permitAll()
//	.and()
//		.httpBasic()
//	.and()
//		.authorizeRequests().anyRequest().anonymous()
//		.antMatchers("/api","/api/**")
//		.permitAll()
	;

	}
}
