package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
