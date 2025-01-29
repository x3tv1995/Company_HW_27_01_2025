package ru.lesson.company_hw_27_01_2025.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {
     private Long id;
     private String name;
     private List<EmployeeDTO> employeeList;
}
