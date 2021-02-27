package com.example.demo.security;

import com.google.common.collect.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.demo.security.ApplicationUserPermission.*;
public enum ApplicationUserRole {
	
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet( COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
	
	ADMINTRANEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));
	
	
	private final Set<ApplicationUserPermission>permissions;
	
	
	ApplicationUserRole(Set<ApplicationUserPermission>permissions){
		
	   this.permissions=permissions;
	}


	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
public Set<SimpleGrantedAuthority> getGrantedAuthority(){
	
	Set<SimpleGrantedAuthority>permissions=	getPermissions().stream()
	   .map(permission->new SimpleGrantedAuthority(permission.getPermission()))
	  .collect(Collectors.toSet());
	
	permissions.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
	return permissions;
}
	
	
}