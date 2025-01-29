package ru.lesson.company_hw_27_01_2025.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String position;
    private CompanyDTO companyDTO;
}
