package edu.epam.fop.web.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Adilov Abdubosit
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DisciplineCourseId implements Serializable {

    @Column(name = "discipline_id")
    private Long disciplineId;

    @Column(name = "course_id")
    private Long courseId;
}
