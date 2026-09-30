package edu.epam.fop.web.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Adilov Abdubosit
 */
@Entity
@Table(name = "grade")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade {

    @EmbeddedId
    private GradeId id;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    @Setter(AccessLevel.PACKAGE)
    private Student student;

    @MapsId("disciplineId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Min(0)
    @Max(100)
    @Setter
    @Column(name = "value")
    private Integer value;

    public Grade(Student student, Discipline discipline, Integer value) {
        this.id = new GradeId(student.getId(), discipline.getId());
        this.student = student;
        this.discipline = discipline;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Grade(studentId=" + id.getStudentId() + ", disciplineId=" + id.getDisciplineId()
                + ", value=" + value + ")";
    }
}
