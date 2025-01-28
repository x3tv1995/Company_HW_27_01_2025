package ru.lesson.company_hw_27_01_2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lesson.company_hw_27_01_2025.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
