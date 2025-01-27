package ru.lesson.company_hw_27_01_2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "employees", joinColumns = @JoinColumn(name = "id_company"))
    @Column(name = "name")
    private List<Employee> employeeList;
}
