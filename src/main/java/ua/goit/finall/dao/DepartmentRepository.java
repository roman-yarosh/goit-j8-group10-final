package ua.goit.finall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.finall.model.Department;

@Repository
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
