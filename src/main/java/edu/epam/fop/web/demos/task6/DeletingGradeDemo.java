package edu.epam.fop.web.demos.task6;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.GradeId;
import edu.epam.fop.web.jpa.repository.GradeRepository;

/**
 * @author Adilov Abdubosit
 */
public class DeletingGradeDemo {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected 2 argument(s), got " + args.length);
            return;
        }
        try {
            long studentId = Long.parseLong(args[0]);
            long disciplineId = Long.parseLong(args[1]);
            new GradeRepository().delete(new GradeId(studentId, disciplineId));
            System.out.println("Deleted grade of student " + studentId + " for discipline " + disciplineId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
