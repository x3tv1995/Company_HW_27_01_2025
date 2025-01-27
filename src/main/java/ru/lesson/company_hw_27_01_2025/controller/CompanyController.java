package ru.lesson.company_hw_27_01_2025.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lesson.company_hw_27_01_2025.companyService.CompanyService;
import ru.lesson.company_hw_27_01_2025.dto.CompanyDTO;
import ru.lesson.company_hw_27_01_2025.dto.EmployeeDTO;


import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/add")
    public CompanyDTO postMapping(@RequestBody @Valid CompanyDTO companyDTO, @RequestParam(value = "employees", required = false) List<EmployeeDTO> employeeDTOS) {

        return companyService.save(companyDTO, employeeDTOS);
    }

    @GetMapping("/{id}")
    public CompanyDTO getMapping(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.getCompanyById(id);
        if (companyDTO == null) {
            return null;
        }
        return companyDTO;

    }
}
