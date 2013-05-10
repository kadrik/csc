package otanga;

import com.google.appengine.api.datastore.Entity;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 5/10/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */

public class DatastoreModel implements Model{

    private final Entity _entity;

    public DatastoreModel(Entity entity) {
        if (entity == null)
            throw new IllegalArgumentException();

        _entity = entity;
    }

    public final Entity getEntity() {
        return _entity;
    }

    @Override
    public Map<String, Object> getProperties() {
        return _entity.getProperties();
    }

    @Override
    public boolean hasProperty(String propertyName) {
        return _entity.hasProperty(propertyName);
    }

    @Override
    public Object getProperty(String propertyName) {
        return _entity.getProperty(propertyName);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        _entity.setProperty(propertyName, value);
    }

    @Override
    public void removeProperty(String propertyName) {
        _entity.removeProperty(propertyName);
    }

}
