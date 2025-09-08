package edu.epam.fop.web.demos.task8;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.List;

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
        return null; // TODO write your code here
    }

    private static Statistics getStatistics(SessionFactory sessionFactory) {
        return null; // TODO write your code here
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

    private static List<Long> saveTwoCourses(SessionFactory sessionFactory) {
        return null; // TODO write your code here
    }

    private static void cleanupCachesAndEnableStats(SessionFactory sessionFactory, Statistics stats) {
        // TODO write your code here
    }

    private static void getCourse1WithinOneSessionTwice(SessionFactory sessionFactory, Long id) {
        // TODO write your code here
    }

    private static void cleanupAllCache(SessionFactory sessionFactory) {
        // TODO write your code here
    }

    private static void getCourse(SessionFactory sessionFactory, Long id) {
        // TODO write your code here
    }
}
