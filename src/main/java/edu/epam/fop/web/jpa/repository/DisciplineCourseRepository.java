package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.entity.DisciplineCourse;
import edu.epam.fop.web.jpa.entity.DisciplineCourseId;
import edu.epam.fop.web.jpa.entity.Grading;

/**
 * @author Adilov Abdubosit
 */
public class DisciplineCourseRepository implements JpaRepository<DisciplineCourse, DisciplineCourseId> {

    @Override
    public Class<DisciplineCourse> getEntityClass() {
        return DisciplineCourse.class;
    }

    @Override
    public void copyValues(DisciplineCourse source, DisciplineCourse target) {
        target.setGrading(source.getGrading());
    }

    public DisciplineCourse link(long courseId, long disciplineId) {
        return getSessionFactory().fromTransaction(session -> {
            if (session.get(DisciplineCourse.class, new DisciplineCourseId(disciplineId, courseId)) != null) {
                throw new IllegalArgumentException("Course " + courseId + " is already linked to discipline " + disciplineId);
            }
            Course course = session.get(Course.class, courseId);
            if (course == null) {
                throw new IllegalArgumentException("Course not found: " + courseId);
            }
            Discipline discipline = session.get(Discipline.class, disciplineId);
            if (discipline == null) {
                throw new IllegalArgumentException("Discipline not found: " + disciplineId);
            }
            DisciplineCourse disciplineCourse = new DisciplineCourse(discipline, course);
            session.persist(disciplineCourse);
            return disciplineCourse;
        });
    }

    public DisciplineCourse updateGrading(long courseId, long disciplineId, Grading grading) {
        return getSessionFactory().fromTransaction(session -> {
            DisciplineCourse disciplineCourse =
                    session.get(DisciplineCourse.class, new DisciplineCourseId(disciplineId, courseId));
            if (disciplineCourse == null) {
                throw new IllegalArgumentException(
                        "Course " + courseId + " is not linked to discipline " + disciplineId);
            }
            disciplineCourse.setGrading(grading);
            return disciplineCourse;
        });
    }
}
