package com.ajeet.controller;

public class CustomLogin {
	
	public String getApplicationRole(String userName, String password, String role)
	{
		if(userName.equals("Ajeet"))
		{
			return "ROLE_ADMIN";
		}
		return null;
	}

}
