package com.examportal.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	public String generateToken(Authentication authentication, String userType) {
		String username= authentication.getName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime()+ SecurityConstants.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECERT)
				.claim("usertype", userType)
				.compact();
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.JWT_SECERT)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public String getUserTypeFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.JWT_SECERT)
				.parseClaimsJws(token)
				.getBody();
		return claims.get("usertype").toString();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECERT).parseClaimsJws(token);
			return true;
		}
		catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT token is not valid " + token);
		}
	}
}
