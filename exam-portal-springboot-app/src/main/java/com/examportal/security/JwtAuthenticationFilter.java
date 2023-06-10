package com.examportal.security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.examportal.model.UserType;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtGenerator jwtGenerator;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getJWTfromRequest(request);
		if(token != null && jwtGenerator.validateToken(token)) {
			String username = jwtGenerator.getUsernameFromJWT(token);
			String userType = jwtGenerator.getUserTypeFromJWT(token);
			customUserDetailsService.setUserType(UserType.valueOf(userType));
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
																			null, userDetails.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getJWTfromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken!=null &&  bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		} else {
			return null;
		}
	}

}
