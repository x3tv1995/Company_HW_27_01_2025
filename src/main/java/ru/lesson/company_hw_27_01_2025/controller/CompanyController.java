package ru.lesson.company_hw_27_01_2025.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lesson.company_hw_27_01_2025.companyService.CompanyService;
import ru.lesson.company_hw_27_01_2025.dto.CompanyDTO;
import ru.lesson.company_hw_27_01_2025.dto.EmployeeDTO;
import ru.lesson.company_hw_27_01_2025.entity.Company;
import ru.lesson.company_hw_27_01_2025.entity.Employee;
import ru.lesson.company_hw_27_01_2025.request.CompanyRequest;


import java.util.List;

@RestController
//@RestController — это аннотация из Spring Framework, которая является комбинацией двух других аннотаций: @Controller и @ResponseBody.
//Она используется для обозначения классов, которые обрабатывают входящие HTTP-запросы и возвращают данные в формате, который может быть отправлен клиенту (например, JSON, XML).
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final   CompanyService companyService;
    private   ModelMapper modelMapper = new ModelMapper();



    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody @Valid CompanyRequest companyRequest) { //ResponseEntity: используется для построения HTTP-ответа, и позволяет устанавливать код состояния.
                                                                                                         //CompanyDTO: Это DTO-объект, который будет возвращен в теле ответа.
        if (companyRequest == null) {
            throw new RuntimeException("CompanyRequest cannot be null");
        }
        System.out.println("Received companyDTO: " + companyRequest.getCompanyDTO());
        System.out.println("Received employeeDTOList: " + companyRequest.getEmployeeDTOList());
        CompanyDTO companyDTO = companyRequest.getCompanyDTO();
        List<EmployeeDTO> employeeDTOList = companyRequest.getEmployeeDTOList();
        if (companyDTO == null) {
            throw new RuntimeException("companyDTO cannot be null");
        }
        Company company =  companyService.createCompany(companyDTO);
        if(employeeDTOList != null){

        }
        List<Employee> employees = companyService.createEmployees(company.getId(),employeeDTOList);
        company.setEmployeeList(employees);


        return ResponseEntity.ok(modelMapper.map(company, CompanyDTO.class)); //  ResponseEntity.ok(...): Создает ResponseEntity с кодом состояния 200 (OK) и телом ответа, содержащим companyDTO.
                                                                              //  Зачем: Возвращает успешный HTTP-ответ с CompanyDTO.

    }



    @GetMapping("/{id}")  //@GetMapping("/{id}") — это аннотация в Spring Framework, которая является сокращением для @RequestMapping(method = RequestMethod.GET, value = "/{id}").
                             // Она используется для сопоставления HTTP GET-запросов к определенному методу в контроллере Spring.
                             // В данном случае, она указывает, что метод, который она аннотирует, должен обрабатывать GET-запросы, где URL содержит переменную пути {id}.
    public CompanyDTO getMapping(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.getCompanyById(id);
        if (companyDTO == null) {
            return null;
        }
        return companyDTO;

    }
    @GetMapping("/countEmployee/{id}")
    public  int  getMappingCountEployee(@PathVariable Long id) {
        return companyService.getEmployeeCount(id);
    }
}
