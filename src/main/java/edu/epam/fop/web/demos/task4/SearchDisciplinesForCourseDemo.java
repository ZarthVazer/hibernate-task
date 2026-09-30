package edu.epam.fop.web.demos.task4;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.repository.DisciplineRepository;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class SearchDisciplinesForCourseDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long courseId = Long.parseLong(args[0]);
            List<Discipline> disciplines = new DisciplineRepository().findByCourse(courseId);
            System.out.println("Disciplines of course " + courseId + ": " + disciplines.size());
            disciplines.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
