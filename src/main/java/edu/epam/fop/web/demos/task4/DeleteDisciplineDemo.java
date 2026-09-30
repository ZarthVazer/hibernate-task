package edu.epam.fop.web.demos.task4;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.repository.DisciplineRepository;

/**
 * @author Adilov Abdubosit
 */
public class DeleteDisciplineDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            long id = Long.parseLong(args[0]);
            new DisciplineRepository().delete(id);
            System.out.println("Deleted discipline " + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
