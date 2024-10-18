package arden.java.employeeservice;

import arden.java.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(EmployeeServiceApplication.class, args);
        EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
        employeeService.findById(1L);
        employeeService.groupByName();
        employeeService.findBetween(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 12, 1));
    }

}
