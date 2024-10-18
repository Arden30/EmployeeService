package arden.java.employeeservice.repository;

import arden.java.employeeservice.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employees WHERE id = :id", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    @Query(value = "SELECT name FROM employees GROUP BY name", nativeQuery = true)
    List<String> groupByName();
}
