package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Discipline;
import edu.epam.fop.web.jpa.entity.Grading;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class DisciplineRepository implements JpaRepository<Discipline, Long> {

    @Override
    public Class<Discipline> getEntityClass() {
        return Discipline.class;
    }

    @Override
    public void copyValues(Discipline source, Discipline target) {
        target.setName(source.getName());
    }

    public List<Discipline> findByCourse(long courseId) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("select d from DisciplineCourse dc join dc.discipline d "
                        + "where dc.id.courseId = :courseId order by d.id", Discipline.class)
                .setParameter("courseId", courseId)
                .list());
    }

    public List<Discipline> findByCourseAndGrading(long courseId, Grading grading) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("select d from DisciplineCourse dc join dc.discipline d "
                        + "where dc.id.courseId = :courseId and dc.grading = :grading "
                        + "order by d.id", Discipline.class)
                .setParameter("courseId", courseId)
                .setParameter("grading", grading)
                .list());
    }
}
