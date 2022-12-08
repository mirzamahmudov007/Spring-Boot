package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByNameAndAndLastNameAndEmailEndingWith(String name, String lastName, String emailEnding);

    @Query(value = "select * from Employee e where e.name = :name and e.last_Name = :lastName",
           nativeQuery = true)
    List<Employee> findAll(@Param("name") String name,
                           @Param("lastName") String lastName);

    @Query(value = "select * from Employee e where e.last_name like :jumla% order by e.name desc", nativeQuery = true)
    List<Employee> getSearch(@Param("jumla") String jumla);

}
