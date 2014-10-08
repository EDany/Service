package models;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Edgar on 07/10/2014.
 */
public class EntityConnection {

    private static EntityManager entity;

    private EntityConnection(){}

    public static EntityManager getEntity(){
        if(entity==null) {
            entity = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();
        }
        return entity;
    }
}
