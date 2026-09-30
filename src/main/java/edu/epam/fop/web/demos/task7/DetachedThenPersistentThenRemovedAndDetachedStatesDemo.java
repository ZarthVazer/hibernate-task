package edu.epam.fop.web.demos.task7;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Shows the path detached, then persistent, then removed, then detached again.
 * <p>
 * Removed: after {@code session.remove()} the object is marked for deletion. The session
 * no longer treats it as managed, and the DELETE is executed when the transaction commits.
 * After the session is closed the Java object still exists and still has its old id,
 * but there is no row behind it any more.
 *
 * @author Adilov Abdubosit
 */
public class DetachedThenPersistentThenRemovedAndDetachedStatesDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            // Create the course and close the session, so the object ends up detached
            Course course = new Course("Course to remove");
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(course);
                transaction.commit();
            }
            Long id = course.getId();
            System.out.println("Detached: " + course);

            Course managed;
            try (Session session = sessionFactory.openSession()) {
                // Detached: this session does not contain our object
                System.out.println("Detached: contains = " + session.contains(course));

                Transaction transaction = session.beginTransaction();

                // Persistent: load the row in the new session, this instance is managed
                managed = session.get(Course.class, id);
                System.out.println("Persistent: contains = " + session.contains(managed));

                // Removed: the object is scheduled for deletion. contains() already returns false,
                // but the row is deleted only on commit. The id is still set on the object.
                session.remove(managed);
                System.out.println("Removed: contains = " + session.contains(managed) + ", id = " + managed.getId());

                // DELETE FROM course WHERE id = ? is executed here
                transaction.commit();
            }

            // Detached again: the session is closed, the object is just a Java object now
            System.out.println("Detached after remove: " + managed);

            // Check that the row really is gone: get() returns null
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Loaded from database: " + session.get(Course.class, id));
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
