package edu.epam.fop.web.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

/**
 * @author Adilov Abdubosit
 */
@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private Double averageGrade;

    public Student(String name) {
        this.name = name;
    }

    public void setGrades(List<Grade> newGrades) {
        if (newGrades != grades) {
            grades.clear();
            if (newGrades != null) {
                newGrades.forEach(grade -> grade.setStudent(this));
                grades.addAll(newGrades);
            }
        }
        recalculateAverageGrade();
    }

    public void addGrade(Grade grade) {
        grade.setStudent(this);
        grades.add(grade);
        recalculateAverageGrade();
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
        recalculateAverageGrade();
    }

    public Double getAverageGrade() {
        if (averageGrade == null) {
            recalculateAverageGrade();
        }
        return averageGrade;
    }

    private void recalculateAverageGrade() {
        if (grades == null || !Hibernate.isInitialized(grades)) {
            averageGrade = null;
            return;
        }
        OptionalDouble average = grades.stream()
                .map(Grade::getValue)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average();
        averageGrade = average.isPresent() ? average.getAsDouble() : null;
    }

    @Override
    public String toString() {
        Long departmentId = department == null ? null : department.getId();
        return "Student(id=" + id + ", name=" + name + ", active=" + active + ", departmentId=" + departmentId + ")";
    }
}
