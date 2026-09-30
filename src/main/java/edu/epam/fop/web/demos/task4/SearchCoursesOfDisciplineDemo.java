package edu.epam.fop.web.demos.task4;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class SearchCoursesOfDisciplineDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long disciplineId = Long.parseLong(args[0]);
            List<Course> courses = new CourseRepository().findByDiscipline(disciplineId);
            System.out.println("Courses of discipline " + disciplineId + ": " + courses.size());
            courses.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
