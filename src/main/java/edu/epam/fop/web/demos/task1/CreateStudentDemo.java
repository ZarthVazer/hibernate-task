package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Student;
import edu.epam.fop.web.jpa.repository.StudentRepository;

/**
 * @author Adilov Abdubosit
 */
public class CreateStudentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            Student student = new Student(args[0]);
            new StudentRepository().save(student);
            System.out.println("Created: " + student);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
