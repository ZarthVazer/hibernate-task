package edu.epam.fop.web.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Adilov Abdubosit
 */
@Entity
@Table(name = "discipline_course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisciplineCourse {

    @EmbeddedId
    private DisciplineCourseId id;

    @MapsId("disciplineId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "grading")
    private Grading grading;

    public DisciplineCourse(Discipline discipline, Course course) {
        this.id = new DisciplineCourseId(discipline.getId(), course.getId());
        this.discipline = discipline;
        this.course = course;
    }

    @Override
    public String toString() {
        return "DisciplineCourse(disciplineId=" + id.getDisciplineId() + ", courseId=" + id.getCourseId()
                + ", grading=" + grading + ")";
    }
}
