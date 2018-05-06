package com.goyal.springsurvey.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication: User --> Roles
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user1").password("{noop}secret1").roles("USER")
			.and().withUser("admin1").password("{noop}secret1").roles("USER", "ADMIN");
	}

	// Authorization: Role --> Access
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
			.antMatchers("/surveys/**").hasRole("USER")
			.antMatchers("/users/**").hasRole("USER")
			.antMatchers("/**").hasRole("ADMIN")
			.and().csrf().disable().headers().frameOptions()
			.disable();
	}
}
