package edu.epam.fop.web.demos.task4;

import edu.epam.fop.web.jpa.HibernateUtil;
import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.repository.DisciplineRepository;

/**
 * @author Adilov Abdubosit
 */
public class CreateDisciplineDemo {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument(s), got " + args.length);
            return;
        }
        try {
            Discipline discipline = new Discipline(args[0]);
            new DisciplineRepository().save(discipline);
            System.out.println("Created: " + discipline);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
