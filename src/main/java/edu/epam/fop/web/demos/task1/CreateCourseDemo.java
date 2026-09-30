package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;

/**
 * @author Adilov Abdubosit
 */
public class CreateCourseDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            Course course = new Course(args[0]);
            new CourseRepository().save(course);
            System.out.println("Created: " + course);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
