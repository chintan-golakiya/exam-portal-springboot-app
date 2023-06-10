package com.examportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.examportal.model.UserType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.requestMatchers("/api/public/**").hasAuthority(UserType.TEACHER.toString())
			.requestMatchers("/api/v1/adminRegister").permitAll()
			.requestMatchers("/api/v1/adminLogin").permitAll()
			.requestMatchers("/api/v1/teacherLogin").permitAll()
			.requestMatchers("/api/v1/studentRegister").permitAll()
			.requestMatchers("/api/v1/studentLogin").permitAll()
			.requestMatchers("/api/v1/admin/**").hasAuthority(UserType.ADMIN.toString())
			.anyRequest().authenticated()
			.and()
			.httpBasic();
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(); 
	}
}
