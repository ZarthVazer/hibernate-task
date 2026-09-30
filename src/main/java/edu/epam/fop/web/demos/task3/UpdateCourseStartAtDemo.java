package edu.epam.fop.web.demos.task3;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.service.CourseService;

import java.time.OffsetDateTime;

/**
 * @author Adilov Abdubosit
 */
public class UpdateCourseStartAtDemo {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected 2 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            OffsetDateTime startAt = "null".equalsIgnoreCase(args[1]) ? null : OffsetDateTime.parse(args[1]);
            System.out.println("Updated: " + new CourseService().updateStartAt(id, startAt));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
