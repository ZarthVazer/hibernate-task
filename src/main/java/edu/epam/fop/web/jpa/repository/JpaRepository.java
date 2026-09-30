package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * @param <Entity> - type of JPA entity
 * @param <Id>     - type of primary key
 *
 * @author Adilov Abdubosit
 */
public interface JpaRepository<Entity, Id> {

    Class<Entity> getEntityClass();

    /**
     * Copies updatable fields from {@code source} to the managed {@code target}.
     */
    default void copyValues(Entity source, Entity target) {
        throw new UnsupportedOperationException(getEntityClass().getSimpleName() + " can't be updated");
    }

    default SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    @SuppressWarnings("unchecked")
    default Id save(Entity entity) {
        return getSessionFactory().fromTransaction(session -> {
            session.persist(entity);
            return (Id) session.getIdentifier(entity);
        });
    }

    default Entity find(Id id) {
        return getSessionFactory().fromSession(session -> session.get(getEntityClass(), id));
    }

    default Entity update(Id id, Entity newValues) {
        return getSessionFactory().fromTransaction(session -> {
            Entity entity = session.get(getEntityClass(), id);
            if (entity == null) {
                throw new IllegalArgumentException(getEntityClass().getSimpleName() + " not found: " + id);
            }
            copyValues(newValues, entity);
            return entity;
        });
    }

    default void delete(Id id) {
        getSessionFactory().inTransaction(session -> {
            Entity entity = session.get(getEntityClass(), id);
            if (entity == null) {
                throw new IllegalArgumentException(getEntityClass().getSimpleName() + " not found: " + id);
            }
            session.remove(entity);
        });
    }
}
