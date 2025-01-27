package ru.lesson.company_hw_27_01_2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lesson.company_hw_27_01_2025.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
