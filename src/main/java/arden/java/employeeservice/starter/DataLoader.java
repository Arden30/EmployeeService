package arden.java.employeeservice.starter;

import arden.java.employeeservice.model.Employee;
import arden.java.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader {
    private final EmployeeRepository employeeRepository;
    private final static List<String> NAMES = List.of("Denis", "Oleg", "Igor", "Valeriy", "Evgeniy");
    private final static List<String> SURNAMES = List.of("Arsenev", "Smirnov", "Trofimov", "Egorov", "Borisov");
    private final static List<String> DEPARTMENTS = List.of("Backend", "Frontend", "QA", "Designer", "Mobile");
    private final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        if (employeeRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Employee employee = Employee.builder()
                        .name(getRandomName())
                        .surname(getRandomLastName())
                        .dateOfBirth(getRandomBirthDate())
                        .department(getRandomDepartment())
                        .salary(getRandomSalary())
                        .build();
                employeeRepository.save(employee);
            }
            log.info("Загружено {} работников в базу данных", employeeRepository.count());
        }
    }

    private String getRandomName() {
        return NAMES.get(random.nextInt(NAMES.size()));
    }

    private String getRandomLastName() {
        return SURNAMES.get(random.nextInt(SURNAMES.size()));
    }

    private LocalDate getRandomBirthDate() {
        int year = getRandomNumberInRange(1970, 2010);
        int month = getRandomNumberInRange(1, 12);
        int day = getRandomNumberInRange(1, 31);

        return LocalDate.of(year, month, day);
    }

    private String getRandomDepartment() {
        return DEPARTMENTS.get(random.nextInt(DEPARTMENTS.size()));
    }

    private double getRandomSalary() {
        return getRandomNumberInRange(30000, 200000);
    }

    private int getRandomNumberInRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
