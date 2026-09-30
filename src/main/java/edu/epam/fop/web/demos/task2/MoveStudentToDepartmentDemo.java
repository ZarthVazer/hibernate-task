package edu.epam.fop.web.demos.task2;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.repository.StudentRepository;

/**
 * @author Adilov Abdubosit
 */
public class MoveStudentToDepartmentDemo {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected 2 argument(s), got " + args.length);
            return;
        }
        try {
            long studentId = Long.parseLong(args[0]);
            long departmentId = Long.parseLong(args[1]);
            System.out.println("Moved: " + new StudentRepository().moveToDepartment(studentId, departmentId));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
