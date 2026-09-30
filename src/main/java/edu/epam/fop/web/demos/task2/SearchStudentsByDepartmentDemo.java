package edu.epam.fop.web.demos.task2;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Student;
import edu.epam.fop.web.jpa.repository.StudentRepository;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class SearchStudentsByDepartmentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long departmentId = Long.parseLong(args[0]);
            List<Student> students = new StudentRepository().findByDepartment(departmentId);
            System.out.println("Students in department " + departmentId + ": " + students.size());
            students.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
