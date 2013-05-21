package csc.Models;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 5/10/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */

public interface Model {

    public abstract Map<String, Object> getProperties();

    public abstract boolean hasProperty(String propertyName);

    public abstract Object getProperty(String propertyName);

    public abstract void setProperty(String propertyName, Object value);

    public abstract void removeProperty(String propertyName);

}
