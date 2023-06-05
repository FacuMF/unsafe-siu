package com.unsafesiu.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	ALUMNO,
	PROFESOR,
	RECTOR;
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	    return authorities;
	  }
}
