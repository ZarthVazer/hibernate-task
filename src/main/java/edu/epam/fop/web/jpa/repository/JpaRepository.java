package edu.epam.fop.web.jpa.repository;

/**
 * @param <Entity> - type of JPA entity
 * @param <Id>     - type of primary key
 */
public interface JpaRepository<Entity, Id> {

    default Id save(Entity entity) {
        // TODO write your code here
        throw new UnsupportedOperationException();
    }

    default Entity find(Id id) {
        // TODO write your code here
        throw new UnsupportedOperationException();
    }

    default Entity update(Id id, Entity newValues) {
        // TODO write your code here
        throw new UnsupportedOperationException();
    }

    default void delete(Id id) {
        // TODO write your code here
        throw new UnsupportedOperationException();
    }
}
