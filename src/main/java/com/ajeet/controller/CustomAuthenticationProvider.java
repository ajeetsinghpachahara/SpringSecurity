package com.ajeet.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class CustomAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication authentication ) throws AuthenticationException {
		String userName = authentication.getName().trim();
		//String userName = authentication.getName().trim();
		String password = authentication.getCredentials().toString().trim();
		Authentication auth = null;
		CustomLogin login = new CustomLogin();
		//Authenticate the user based on your custom logic
		String  role = login.getApplicationRole(userName, password, "ADMIN");
		if (role != null)
		{
			
			Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority(role));
			ApplicationUser appUser = new ApplicationUser(userName,password, true, true, true, true,grantedAuths,"TestEmail");
			auth = new UsernamePasswordAuthenticationToken(appUser, password, grantedAuths);
			return auth;
		}
		else 
		{
			return null;
		}
	}
	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
