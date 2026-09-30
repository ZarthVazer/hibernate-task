package edu.epam.fop.web.demos.task5;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Grading;
import edu.epam.fop.web.jpa.repository.DisciplineCourseRepository;

/**
 * @author Adilov Abdubosit
 */
public class UpdatingDisciplineCourseRelationDemo {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Expected 3 argument(s), got " + args.length);
            return;
        }
        try {
            long courseId = Long.parseLong(args[0]);
            long disciplineId = Long.parseLong(args[1]);
            Grading grading = Grading.parse(args[2]);
            System.out.println("Updated: " + new DisciplineCourseRepository().updateGrading(courseId, disciplineId, grading));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
