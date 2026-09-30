package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.entity.Grade;
import edu.epam.fop.web.jpa.entity.GradeId;
import edu.epam.fop.web.jpa.entity.Student;

/**
 * @author Adilov Abdubosit
 */
public class GradeRepository implements JpaRepository<Grade, GradeId> {

    @Override
    public Class<Grade> getEntityClass() {
        return Grade.class;
    }

    @Override
    public void copyValues(Grade source, Grade target) {
        target.setValue(source.getValue());
    }

    public Grade addOrUpdate(long studentId, long disciplineId, int value) {
        return getSessionFactory().fromTransaction(session -> {
            Grade grade = session.get(Grade.class, new GradeId(studentId, disciplineId));
            if (grade != null) {
                grade.setValue(value);
                return grade;
            }
            Student student = session.get(Student.class, studentId);
            if (student == null) {
                throw new IllegalArgumentException("Student not found: " + studentId);
            }
            Discipline discipline = session.get(Discipline.class, disciplineId);
            if (discipline == null) {
                throw new IllegalArgumentException("Discipline not found: " + disciplineId);
            }
            grade = new Grade(student, discipline, value);
            session.persist(grade);
            return grade;
        });
    }
}
