package edu.epam.fop.web.demos.task8;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Checks that the second-level cache works for Course.
 * <p>
 * Hibernate has two cache levels:
 * <ul>
 *     <li>first-level cache - the Session itself, it lives only while the session is open;</li>
 *     <li>second-level cache - belongs to the SessionFactory and is shared by all sessions.
 *     Here it is Ehcache (configured in ehcache.xml). Course is cached because it has
 *     {@code @Cacheable} and {@code @Cache(usage = READ_WRITE)}.</li>
 * </ul>
 * When a Course is loaded by id, Hibernate looks in the first-level cache, then in the
 * second-level cache, and only then runs a SELECT. The statistics show what happened:
 * hit - found in the second-level cache, miss - not found there,
 * put - loaded from the database and stored in the second-level cache.
 *
 * @author Adilov Abdubosit
 */
public class CacheDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();
        Statistics stats = getStatistics(sessionFactory);

        List<Long> ids = saveTwoCourses(sessionFactory);
        Long course1Id = ids.get(0);
        Long course2Id = ids.get(1);

        cleanupCachesAndEnableStats(sessionFactory, stats);
        printData(stats, 0,0,0, "cleanupCachesAndEnableStats"); // expected 0-0-0
        getCourse1WithinOneSessionTwice(sessionFactory, course1Id);
        printData(stats, 0, 1, 1, "getCourse1WithinOneSessionTwice"); // 0-1-1
        getCourse(sessionFactory, course1Id);
        printData(stats, 1, 1, 1, "get course 1"); // 1-1-1
        getCourse(sessionFactory, course2Id);
        printData(stats, 1, 2, 2, "get course 2"); // 1-2-2
        getCourse(sessionFactory, course2Id);
        printData(stats, 2, 2, 2, "get course 2");
        cleanupAllCache(sessionFactory);
        System.out.println("cleanupAllCache");
        getCourse(sessionFactory, course1Id);
        printData(stats, 2, 3, 3, "get course 1"); // 2-3-3
        getCourse(sessionFactory, course2Id);
        printData(stats, 2, 4, 4, "get course 2"); // 2-4-4
        getCourse(sessionFactory, course1Id);
        printData(stats, 3, 4, 4, "get course 1"); // 3-4-4
        getCourse(sessionFactory, course2Id);
        printData(stats, 4, 4, 4, "get course 2"); // 4-4-4
        getCourse(sessionFactory, 123L);
        printData(stats, 4, 5, 4, "get unknown course"); // 4-5-4

        sessionFactory.close();
    }

    private static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    private static Statistics getStatistics(SessionFactory sessionFactory) {
        return sessionFactory.getStatistics();
    }

    private static void printData(Statistics stats, int hit, int miss, int put, String operation) {
        System.out.println();
        System.out.println("***** Second Level Cache STATS ***** operation = " + operation);
        System.out.print("exp Hit=" + stats.getSecondLevelCacheHitCount() + " ");
        System.out.print("Miss=" + stats.getSecondLevelCacheMissCount() + " ");
        System.out.println("Put=" + stats.getSecondLevelCachePutCount() + " ");
        System.out.print("act Hit=" + hit + " ");
        System.out.print("Miss=" + miss + " ");
        System.out.println("Put=" + put + " ");
        System.out.println();
    }

    // Creates two courses that will be loaded later. Saving can already put them into
    // the second-level cache, that is why the caches are cleaned up right after this step.
    private static List<Long> saveTwoCourses(SessionFactory sessionFactory) {
        CourseRepository repository = new CourseRepository();
        Long course1Id = repository.save(new Course("cache-course-1"));
        Long course2Id = repository.save(new Course("cache-course-2"));
        return List.of(course1Id, course2Id);
    }

    // Removes everything from the second-level cache and resets the counters,
    // so every following step starts from a known state: hit=0, miss=0, put=0.
    private static void cleanupCachesAndEnableStats(SessionFactory sessionFactory, Statistics stats) {
        sessionFactory.getCache().evictAllRegions();
        stats.setStatisticsEnabled(true);
        stats.clear();
    }

    // The first get() does not find the course in any cache: miss +1, the course is read
    // from the database and put into the second-level cache: put +1.
    // The second get() in the same session is served by the first-level cache (the session
    // already holds this object), so the second-level cache is not asked at all and the
    // counters do not change. Result: 0-1-1.
    private static void getCourse1WithinOneSessionTwice(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.get(Course.class, id);
            session.get(Course.class, id);
        }
    }

    // Empties the whole second-level cache. Courses loaded after this call
    // are not found there (miss) and have to be read from the database again (put).
    private static void cleanupAllCache(SessionFactory sessionFactory) {
        sessionFactory.getCache().evictAllRegions();
    }

    // Every call opens a new session, so the first-level cache is always empty
    // and the course can only come from the second-level cache (hit) or from the database
    // (miss + put). For an id that does not exist there is a miss but nothing to put.
    // session.clear() is called only to show that the first-level cache is emptied
    // before the session is closed; the next call starts with a clean session anyway.
    private static void getCourse(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Course course = session.get(Course.class, id);
            System.out.println("Loaded: " + course);
            session.clear();
        }
    }
}
