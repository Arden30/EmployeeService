package arden.java.employeeservice.service;

import arden.java.employeeservice.model.Employee;
import arden.java.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(id);

        if (employee.isEmpty()) {
            log.error("Работник с id {} не найден", id);
        } else log.info(employee.get().toString());

        return employee;
    }

    public List<String> groupByName() {
        List<String> employees = employeeRepository.groupByName();
        log.info("Сгруппированный список сотрудников по имени: {}", employees);

        return employees;
    }
}
