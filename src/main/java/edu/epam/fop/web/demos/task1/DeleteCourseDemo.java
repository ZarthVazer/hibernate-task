package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.repository.CourseRepository;

/**
 * @author Adilov Abdubosit
 */
public class DeleteCourseDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            new CourseRepository().delete(id);
            System.out.println("Deleted course " + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
