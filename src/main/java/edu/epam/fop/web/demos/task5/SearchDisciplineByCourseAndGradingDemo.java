package edu.epam.fop.web.demos.task5;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.entity.Grading;
import edu.epam.fop.web.jpa.repository.DisciplineRepository;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class SearchDisciplineByCourseAndGradingDemo {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected 2 argument(s), got " + args.length);
            return;
        }
        try {
            long courseId = Long.parseLong(args[0]);
            Grading grading = Grading.parse(args[1]);
            List<Discipline> disciplines = new DisciplineRepository().findByCourseAndGrading(courseId, grading);
            System.out.println("Disciplines of course " + courseId + " with grading " + grading + ": " + disciplines.size());
            disciplines.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
