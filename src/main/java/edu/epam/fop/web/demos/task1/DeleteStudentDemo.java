package edu.epam.fop.web.demos.task1;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.repository.StudentRepository;

/**
 * @author Adilov Abdubosit
 */
public class DeleteStudentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            new StudentRepository().delete(id);
            System.out.println("Deleted student " + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
