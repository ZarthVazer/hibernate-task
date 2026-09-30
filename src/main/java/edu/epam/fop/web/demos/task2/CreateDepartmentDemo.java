package edu.epam.fop.web.demos.task2;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Department;
import edu.epam.fop.web.jpa.repository.DepartmentRepository;

/**
 * @author Adilov Abdubosit
 */
public class CreateDepartmentDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            Department department = new Department(args[0]);
            new DepartmentRepository().save(department);
            System.out.println("Created: " + department);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
