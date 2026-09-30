package edu.epam.fop.web.demos.task6;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Student;
import edu.epam.fop.web.jpa.repository.GradeRepository;
import edu.epam.fop.web.jpa.repository.StudentRepository;
import jakarta.validation.ConstraintViolationException;

/**
 * @author Adilov Abdubosit
 */
public class AddingOrUpdatingGradeDemo {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Expected 3 argument(s), got " + args.length);
            return;
        }
        try {
            long studentId = Long.parseLong(args[0]);
            long disciplineId = Long.parseLong(args[1]);
            int value = Integer.parseInt(args[2]);
            try {
                System.out.println("Saved: " + new GradeRepository().addOrUpdate(studentId, disciplineId, value));
            } catch (ConstraintViolationException e) {
                System.out.println("Grade must be between 0 and 100, got " + value);
                return;
            }
            Student student = new StudentRepository().findWithGrades(studentId);
            System.out.println(student + ", average grade = " + student.getAverageGrade());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
