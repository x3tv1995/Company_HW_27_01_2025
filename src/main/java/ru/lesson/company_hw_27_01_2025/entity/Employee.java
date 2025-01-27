package ru.lesson.company_hw_27_01_2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
public class Employee {

    private String name;
    private String position;

}
