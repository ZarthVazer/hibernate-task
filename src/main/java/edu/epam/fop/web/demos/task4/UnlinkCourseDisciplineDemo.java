package edu.epam.fop.web.demos.task4;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.DisciplineCourseId;
import edu.epam.fop.web.jpa.repository.DisciplineCourseRepository;

/**
 * @author Adilov Abdubosit
 */
public class UnlinkCourseDisciplineDemo {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected 2 argument(s), got " + args.length);
            return;
        }
        try {
            long courseId = Long.parseLong(args[0]);
            long disciplineId = Long.parseLong(args[1]);
            new DisciplineCourseRepository().delete(new DisciplineCourseId(disciplineId, courseId));
            System.out.println("Unlinked course " + courseId + " and discipline " + disciplineId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
