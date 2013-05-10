package otanga;

import com.google.appengine.api.datastore.Entity;

import java.util.Map;

public class Profile extends DatastoreModel{

    public Profile(Entity entity) {
        super(entity);
    }

    public String getUserId() {
		return (String) this.getProperty("userId");
	}
	/*
	public String getUserType() {
		return (String) this.getProperty("userType");
	}
	*/
}
