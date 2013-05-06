package com.otanga;

import com.google.appengine.api.users.*;

public class Otanga {
	static UserService userService;
	static User user;
	static {
		userService = UserServiceFactory.getUserService();
	}
	
	private Otanga(){}

	public static User getUser(){
		return userService.getCurrentUser();
	}
	
	public static String getLoginUrl(String uri){
		return userService.createLoginURL(uri);
	}
	
	public static String getLogoutUrl(String uri){
		return userService.createLogoutURL(uri);
	}
	
	public static String getUserName() {
		return getUser().getNickname();
	}
	//user.getUserId()
	
	public static boolean isUserLoggedIn() {
		return (getUser()!=null);
	}

	
}
