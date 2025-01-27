package ru.lesson.company_hw_27_01_2025.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lesson.company_hw_27_01_2025.entity.Employee;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {

    @NotBlank(message = "Name is not null")
    @Size(min = 2, max = 15, message = "Length 2-15")
    private String name;

    private List<Employee> employeeList;
}
