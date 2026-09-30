package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Department;
import edu.epam.fop.web.jpa.entity.Student;

import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class StudentRepository implements JpaRepository<Student, Long> {

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public void copyValues(Student source, Student target) {
        target.setName(source.getName());
        target.setActive(source.isActive());
    }

    public List<Student> findByDepartment(long departmentId) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("from Student s where s.department.id = :departmentId order by s.id", Student.class)
                .setParameter("departmentId", departmentId)
                .list());
    }

    public Student moveToDepartment(long studentId, long departmentId) {
        return getSessionFactory().fromTransaction(session -> {
            Student student = session.get(Student.class, studentId);
            if (student == null) {
                throw new IllegalArgumentException("Student not found: " + studentId);
            }
            Department department = session.get(Department.class, departmentId);
            if (department == null) {
                throw new IllegalArgumentException("Department not found: " + departmentId);
            }
            student.setDepartment(department);
            return student;
        });
    }

    public Student findWithGrades(long id) {
        return getSessionFactory().fromSession(session -> session
                .createSelectionQuery("select s from Student s "
                        + "left join fetch s.grades g "
                        + "left join fetch g.discipline "
                        + "where s.id = :id", Student.class)
                .setParameter("id", id)
                .uniqueResult());
    }
}
