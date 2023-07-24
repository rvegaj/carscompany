package com.carscompany.service;

import com.carscompany.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();

    String deleteEmployee(Long id) throws Exception;
}
