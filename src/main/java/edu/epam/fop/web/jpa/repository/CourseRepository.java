package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Course;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class CourseRepository implements JpaRepository<Course, Long> {

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    public void copyValues(Course source, Course target) {
        target.setName(source.getName());
        target.setActive(source.isActive());
        target.setStartAt(source.getStartAt());
        target.setEndAt(source.getEndAt());
    }

    public List<Course> findActive(OffsetDateTime now) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("from Course c "
                        + "where c.startAt is not null and c.startAt <= :now "
                        + "and (c.endAt is null or c.endAt > :now) "
                        + "order by c.id", Course.class)
                .setParameter("now", now)
                .list());
    }

    public List<Course> findFuture(OffsetDateTime now) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("from Course c where c.startAt > :now order by c.id", Course.class)
                .setParameter("now", now)
                .list());
    }

    public List<Course> findByDiscipline(long disciplineId) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("select c from DisciplineCourse dc join dc.course c "
                        + "where dc.id.disciplineId = :disciplineId order by c.id", Course.class)
                .setParameter("disciplineId", disciplineId)
                .list());
    }
}
