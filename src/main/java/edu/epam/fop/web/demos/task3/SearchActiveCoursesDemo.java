package edu.epam.fop.web.demos.task3;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.service.CourseService;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class SearchActiveCoursesDemo {

    public static void main(String[] args) {
        try {
            List<Course> courses = new CourseService().findActiveCourses();
            System.out.println("Active courses: " + courses.size());
            courses.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
