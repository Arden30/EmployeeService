package arden.java.employeeservice;

import arden.java.employeeservice.model.Employee;
import arden.java.employeeservice.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class EmployeeRepositoryTest extends TestcontainersConfiguration {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.createNativeQuery("TRUNCATE TABLE employees RESTART IDENTITY").executeUpdate();

        employeeRepository.save(Employee.builder()
                .name("Denis")
                .surname("Arsenev")
                .dateOfBirth(LocalDate.of(2003, 11, 5))
                .department("backend")
                .salary(100000.00)
                .build());
        employeeRepository.save(Employee.builder()
                .name("Alexey")
                .surname("Ivanov")
                .dateOfBirth(LocalDate.of(1990, 1, 20))
                .department("frontend")
                .salary(120000.00)
                .build());
    }

    @Test
    @DisplayName("Поиск по ID существующего работника")
    void testFindEmployeeById_SuccessTest() {
        Optional<Employee> employee = employeeRepository.findEmployeeById(1L);

        assertAll("Проверка ответа",
                () -> assertTrue(employee.isPresent()),
                () -> assertThat(employee.get().getName()).isEqualTo("Denis"));
    }

    @Test
    @DisplayName("Поиск по ID несуществующего работника")
    void testFindEmployeeById_FailTest() {
        Optional<Employee> employee = employeeRepository.findEmployeeById(3L);

        assertTrue(employee.isEmpty());
    }

    @Test
    @DisplayName("Группировка по имени")
    void testGroupByName() {
        List<String> groupedNames = employeeRepository.groupByName();

        assertAll("Проверка ответа",
                () -> assertThat(groupedNames.size()).isEqualTo(2),
                () -> assertTrue(groupedNames.contains("Denis")),
                () -> assertTrue(groupedNames.contains("Alexey")));
    }

    @Test
    @DisplayName("Поиск по дате рождения")
    void testFindBetween() {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2010, 12, 31);
        List<Employee> employees = employeeRepository.findBetween(start, end);


        assertAll("Проверка ответа",
                () -> assertThat(employees.size()).isEqualTo(1),
                () -> assertThat(employees.getFirst().getName()).isEqualTo("Denis"));
    }
}
