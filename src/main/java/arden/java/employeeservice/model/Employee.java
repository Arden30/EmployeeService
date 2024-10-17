package arden.java.employeeservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String department;

    @Min(0)
    @Column(nullable = false)
    private Double salary;
}
