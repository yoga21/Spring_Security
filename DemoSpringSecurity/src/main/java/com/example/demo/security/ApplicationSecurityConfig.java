package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.demo.security.ApplicationUserRole.*;
import static com.example.demo.security.ApplicationUserPermission.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//&& ROLE based Authentication  &&//
//		http.csrf().disable()
//		     .authorizeRequests()
//		     .antMatchers("/","index","/css/*","/js/*").permitAll()
//		     .antMatchers("/api/**").hasRole(STUDENT.name())
//		     .anyRequest()
//		     .authenticated()
//		     .and()
//		     .httpBasic();
		
		
		
		http
		
		/*
		 * // Generate X-XSRF-TOKEN
		 *///		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//		.and()
		.csrf().disable()
	     .authorizeRequests()
	     .antMatchers("/","index","/css/*","/js/*").permitAll()
	     .antMatchers("/api/**").hasRole(STUDENT.name())
//	     .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//
//	     .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//	     .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//	     .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRANEE .name())
	     .anyRequest()
	     .authenticated()	
	     .and()
	     .httpBasic();
	} 

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
	
		UserDetails suyogUser=User.builder()
		    .username("suyog")
		    .password(passwordEncoder.encode("Suyog@21"))
//		    .roles(STUDENT.name())
		    .authorities(STUDENT.getGrantedAuthority())	
		    .build();
		
		
		UserDetails mayuriUser=User.builder()
			    .username("mayuri")
			    .password(passwordEncoder.encode("Mayuri"))
//			    .roles(ADMIN.name())
			    .authorities(ADMIN.getGrantedAuthority())
			    .build();
		
		
		
		UserDetails jadhavUser=User.builder()
			    .username("jadhav")
			    .password(passwordEncoder.encode("Jadhav"))
//			    .roles(ADMINTRANEE.name())
			    .authorities(ADMINTRANEE.getGrantedAuthority())
			    .build();
		
		
		
		return new InMemoryUserDetailsManager(
				
				suyogUser,
				mayuriUser,
				jadhavUser
				) ;
	}
	
	
	
	

	
}
