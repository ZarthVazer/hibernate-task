package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Student;
import edu.epam.fop.web.jpa.repository.StudentRepository;

/**
 * @author Adilov Abdubosit
 */
public class ActivateStudentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            StudentRepository repository = new StudentRepository();
            Student student = repository.find(id);
            if (student == null) {
                System.out.println("Student not found: " + id);
                return;
            }
            student.setActive(true);
            System.out.println("Activated: " + repository.update(id, student));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
