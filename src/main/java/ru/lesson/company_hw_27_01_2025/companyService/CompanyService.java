package ru.lesson.company_hw_27_01_2025.companyService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.lesson.company_hw_27_01_2025.dto.CompanyDTO;
import ru.lesson.company_hw_27_01_2025.dto.EmployeeDTO;
import ru.lesson.company_hw_27_01_2025.entity.Company;
import ru.lesson.company_hw_27_01_2025.entity.Employee;
import ru.lesson.company_hw_27_01_2025.repository.CompanyRepository;
import ru.lesson.company_hw_27_01_2025.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private ModelMapper modelMapper = new ModelMapper();
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;



    public CompanyDTO save(CompanyDTO companyDTO,List<EmployeeDTO> employeeDTOList) {
        Company c = modelMapper.map(companyDTO, Company.class);
        c= companyRepository.save(c);

        Company finalC = c;
        List<Employee> employees = employeeDTOList != null ? employeeDTOList.stream()
                .map(employeeDTO -> {
                    Employee employee = modelMapper.map(employeeDTO, Employee.class);
                    employee.setCompany(finalC);
                    return employee;
                })
                .toList() : new ArrayList<>();

        for (Employee employee : employees) {
            employeeRepository.save(employee);
        }
        c.setEmployeeList(employees);
        return modelMapper.map(c, CompanyDTO.class);

    }

    public CompanyDTO getCompanyById(long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            return modelMapper.map(company, CompanyDTO.class);
        } else {
            return null;
        }
    }

    public int getEmployeeCount(long idCompany) {
        CompanyDTO companyDTO = getCompanyById(idCompany);
        return companyDTO.getEmployeeList().size();
    }

}
