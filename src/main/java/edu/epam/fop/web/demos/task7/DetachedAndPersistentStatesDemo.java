package edu.epam.fop.web.demos.task7;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Shows the detached state and how a detached Course becomes persistent again.
 * <p>
 * Detached: the object was persistent, but its Session was closed (or the object was
 * detached from it). The object still has an id and the row still exists in the
 * database, but changes to the object are not tracked and not saved any more.
 * <p>
 * To make it persistent again we use {@code session.merge()}: it copies the state of the
 * detached object into a managed object and returns that managed object.
 * The example also shows {@code session.refresh()}, which reloads a persistent object
 * from the database.
 *
 * @author Adilov Abdubosit
 */
public class DetachedAndPersistentStatesDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Course course = new Course("Detached course");
        try {
            // Step 1: save the course, inside this session it is persistent
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(course);
                transaction.commit();
                System.out.println("Persistent: contains = " + session.contains(course));
            }

            // Step 2: the session is closed, so the course is detached now.
            // This change is only in memory, nothing is sent to the database.
            course.setName("Changed while detached");

            try (Session session = sessionFactory.openSession()) {
                // The new session does not know about our object
                System.out.println("Detached: contains = " + session.contains(course));

                // get() loads the same row as a new, managed object
                Course loaded = session.get(Course.class, course.getId());
                System.out.println("Loaded object is the same instance: " + (loaded == course));

                // Step 3: merge() copies the detached state into the managed object and returns it.
                // The returned object is persistent, the object we passed stays detached.
                Transaction transaction = session.beginTransaction();
                Course managed = session.merge(course);
                System.out.println("After merge: contains(managed) = " + session.contains(managed)
                        + ", contains(course) = " + session.contains(course));
                transaction.commit();

                // Step 4: change the row directly with an HQL update. The object in the session
                // is not updated by this query, so its name becomes out of date.
                transaction = session.beginTransaction();
                session.createMutationQuery("update Course c set c.name = :name where c.id = :id")
                        .setParameter("name", "Changed in database")
                        .setParameter("id", managed.getId())
                        .executeUpdate();
                System.out.println("Before refresh: " + managed.getName());

                // refresh() reads the row again and overwrites the object's state
                session.refresh(managed);
                System.out.println("After refresh: " + managed.getName());
                transaction.commit();

                // Step 5: an object can also be detached while the session is still open
                session.detach(managed);
                System.out.println("After detach: contains = " + session.contains(managed));
            }
        } finally {
            if (course.getId() != null) {
                new CourseRepository().delete(course.getId());
            }
            HibernateUtil.shutdown();
        }
    }
}
