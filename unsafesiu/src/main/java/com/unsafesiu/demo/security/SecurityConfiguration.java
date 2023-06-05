package com.unsafesiu.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	private  JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private  AuthenticationProvider authenticationProvider;
	
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/login")
	        .permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//	        .logout()
//	        .logoutUrl("/api/v1/auth/logout")
//	        .addLogoutHandler(logoutHandler)
//	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	    ;

	    return http.build();
	  }
}
