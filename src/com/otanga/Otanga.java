package com.otanga;

import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.Entity;	// temporary

public class Otanga {
	private final static UserService userService = UserServiceFactory.getUserService();
	private static User user;
	
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

	public static boolean isProfileStored() {
		return Storage.getUser(getUser().getUserId()) != null;
	}
	
	public static String testStore(){		
		long id = Storage.storeUser(getUser().getUserId());
		if (id >= 0)
		{
			if (Storage.updateUser(getUser().getUserId(), "test"))
			{
				Entity entity = Storage.getUser(getUser().getUserId());
				return (String)entity.getProperty("nickname");
			}
		}
		return "!error!";
	}
	
	
}
