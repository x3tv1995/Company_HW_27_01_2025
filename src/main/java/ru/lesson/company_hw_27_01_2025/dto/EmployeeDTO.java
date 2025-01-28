package ru.lesson.company_hw_27_01_2025.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name is not null")
    @Size(min = 2, max = 15, message = "Length 2-15")
    private String name;


    @NotBlank(message = "Name is not null")
    @Size(min = 2, max = 15, message = "Length 2-15")
    private String position;
}
