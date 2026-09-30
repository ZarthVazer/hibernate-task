package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Student;
import edu.epam.fop.web.jpa.repository.StudentRepository;

/**
 * @author Adilov Abdubosit
 */
public class FindStudentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            Student student = new StudentRepository().find(id);
            System.out.println(student != null ? student : "Student not found: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
