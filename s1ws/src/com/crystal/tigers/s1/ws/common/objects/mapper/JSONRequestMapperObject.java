package com.crystal.tigers.s1.ws.common.objects.mapper;

/**
 * JSON Mapping object, that will handle any requests that send a JSON object to controller
 *  and map to the correct Model
 * @author sonyynoss
 */
public class JSONRequestMapperObject<T> extends S1MapperObject<T> {
    //TODO: T would need to extend Model, but we didn't have an Abstract Model yet

    /**
     * objectName represented the Model ( not required though)
     */
    private String objectName;

    /**
     * The object to be sent. Will have this as "object" : { ... }  Then map to the correct model
     */
    private T object;

    /**
     * Reserved field
     */
    private String key;

    public T getObject() {
        return object;
    }

    public String getKey() {
        return key;
    }

    public String getObjectName() {
        return objectName;
    }
}
