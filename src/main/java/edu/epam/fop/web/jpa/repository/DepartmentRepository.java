package edu.epam.fop.web.jpa.repository;

import edu.epam.fop.web.jpa.entity.Department;

/**
 * @author Adilov Abdubosit
 */
public class DepartmentRepository implements JpaRepository<Department, Long> {

    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Department can't be deleted");
    }
}
