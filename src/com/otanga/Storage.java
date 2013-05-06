package com.otanga;

// import com.google.appengine.api.datastore.DatastoreService;
// import com.google.appengine.api.datastore.DatastoreServiceFactory;
// import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

public class Storage {
	
	private final static DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		
	private Storage() {}
	
	public static Entity getUser(String userId) {
		Filter userIdFilter = new FilterPredicate("userId", FilterOperator.EQUAL, userId);		
		Query q = new Query("Profile").setFilter(userIdFilter);
		
		PreparedQuery pq = dataStore.prepare(q);
		
		for (Entity entity : pq.asIterable()) {
			return entity;
		}
		
		return null;
	}
	
	public static long storeUser(String userId) {
		Entity entity = new Entity("Profile");
		entity.setProperty("userId", userId);
		
		//TODO: add more properties to the entity
		
		Key entityKey = dataStore.put(entity);
		return (entityKey != null) ? entityKey.getId() : -1;
	}
	
	public static boolean updateUser(String userId, String nickname) {
		
		Entity entity = getUser(userId);
		if (entity == null) return false;
		
		//TODO: update the entity's properties
		entity.setProperty("nickname", nickname);


		return (dataStore.put(entity) != null);
	}

}
