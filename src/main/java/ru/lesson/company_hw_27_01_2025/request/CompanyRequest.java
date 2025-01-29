package ru.lesson.company_hw_27_01_2025.request;

import ru.lesson.company_hw_27_01_2025.dto.CompanyDTO;
import ru.lesson.company_hw_27_01_2025.dto.EmployeeDTO;

import java.util.List;

public class CompanyRequest {

    private CompanyDTO companyDTO;
    private List<EmployeeDTO> employeeDTOList;

    public CompanyRequest() {
    }

    public CompanyRequest(CompanyDTO companyDTO, List<EmployeeDTO> employeeDTOList) {
        this.companyDTO = companyDTO;
        this.employeeDTOList = employeeDTOList;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public List<EmployeeDTO> getEmployeeDTOList() {
        return employeeDTOList;
    }

    public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }
}
