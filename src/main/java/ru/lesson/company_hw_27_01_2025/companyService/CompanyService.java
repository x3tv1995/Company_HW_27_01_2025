package ru.lesson.company_hw_27_01_2025.companyService;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.lesson.company_hw_27_01_2025.dto.CompanyDTO;
import ru.lesson.company_hw_27_01_2025.dto.EmployeeDTO;
import ru.lesson.company_hw_27_01_2025.entity.Company;
import ru.lesson.company_hw_27_01_2025.entity.Employee;
import ru.lesson.company_hw_27_01_2025.repository.CompanyRepository;
import ru.lesson.company_hw_27_01_2025.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private ModelMapper modelMapper = new ModelMapper();
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;



    public Company createCompany(CompanyDTO companyDTO) {
        try {
            if (companyDTO == null) {
                throw new RuntimeException("companyDTO cannot be null");
            }
            Company company = modelMapper.map(companyDTO, Company.class);
            Company savedCompany = companyRepository.save(company);
            System.out.println("Saved company: " + savedCompany);
            return savedCompany;
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error creating company: " + e.getMessage(), e);//Ловит исключение типа DataAccessException, которое может возникнуть при работе с базой данных.
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error creating company: " + e.getMessage(), e);//если во время работы с базой данных возникнет ошибка, она будет обнаружена и будет выдано более понятное сообщение.
        }

    }

    public List<Employee> createEmployees(Long companyId, List<EmployeeDTO> employeeDTOList) {
        try {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId)); // Если компания с указанным id не найдена, то выбрасывается исключение RuntimeException с сообщением об ошибке.
                                                                                                         //  Зачем: Если компании нет, то нельзя создать сотрудников, поэтому мы выбрасываем исключение.
            if (employeeDTOList == null) {
                throw new RuntimeException("employeeDTOList cannot be null");
            }
            List<Employee> employees = employeeDTOList.stream()
                    .map(employeeDTO -> {                                           // Преобразует каждый объект EmployeeDTO в объект Employee.
                        if (employeeDTO == null) {
                            throw new RuntimeException("employeeDTO cannot be null");
                        }
                        Employee employee = modelMapper.map(employeeDTO, Employee.class);
                        employee.setCompany(company); //Устанавливает для сотрудника компанию, к которой он относится.
                                                      //  Зачем: Привязка сотрудника к компании.

                        return employee;
                    })
                    .collect(Collectors.toList());

            for (Employee employee : employees) {  //Итерируется по списку сотрудников.
                                                   // Зачем: Нужно для того, чтоб сохранить сотрудников.
                employeeRepository.save(employee);
            }

            System.out.println("Saved employees: " + employees);
            return employees;

        } catch (DataAccessException e) {
            throw new RuntimeException("Database error creating employees: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error creating employees: " + e.getMessage(), e);
        }
    }


    public CompanyDTO getCompanyById(long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);  //Optional<Company>. Optional используется для обработки ситуации, когда компания может быть не найдена (т.е. может быть null).
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

