package com.otanga;

import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.Entity;	// temporary

public class Otanga {
	private final static UserService userService = UserServiceFactory.getUserService();
	
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
	
	public static String storeAndUpdateProfile(){		
		long id = Storage.storeUser(getUser().getUserId());
		if (id >= 0)
		{
			if (Storage.updateUser(getUser().getUserId(), "TheNickname"))
			{
				Entity entity = Storage.getUser(getUser().getUserId());
				return (String)entity.getProperty("nickname");
			}
		}
		return "!error!";
	}
	
	public static String getStoredProfile()
	{
		Entity entity = Storage.getUser(getUser().getUserId());
		if (entity != null)
			return (String)entity.getProperty("nickname");
		else
			return "!error!";
	}
	
	
	
}
