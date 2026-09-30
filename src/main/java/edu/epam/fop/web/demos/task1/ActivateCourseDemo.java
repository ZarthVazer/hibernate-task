package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;

/**
 * @author Adilov Abdubosit
 */
public class ActivateCourseDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            CourseRepository repository = new CourseRepository();
            Course course = repository.find(id);
            if (course == null) {
                System.out.println("Course not found: " + id);
                return;
            }
            course.setActive(true);
            System.out.println("Activated: " + repository.update(id, course));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
