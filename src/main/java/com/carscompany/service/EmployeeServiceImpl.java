package com.carscompany.service;

import com.carscompany.common.Constants;
import com.carscompany.common.validator.ValidationService;
import com.carscompany.dao.EmployeeRepository;
import com.carscompany.dto.EmployeeDto;
import com.carscompany.infraestructure.web.exceptions.ExceptionDataQuery;
import com.carscompany.mapper.EmployeeMapper;
import com.carscompany.model.Employee;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
  private final ValidationService validationService;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
      ValidationService validationService) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
    this.validationService = validationService;
  }

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    validationService.validationRequest(employeeDto);
    try{
      return employeeMapper.employeeModelToEmployeeDto(employeeRepository.save(employeeMapper.employeeDtoToEmployeeModel(employeeDto)));
    } catch (Exception e){
      log.error("Error al guardar los datos del empleado", e.getMessage());
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_SAVE_DATA);
    }
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    try{
      return employeeMapper.listEmployeeModelToListEmployeeDto(
          (List<Employee>) employeeRepository.findAll());
    } catch (Exception e){
      log.error("Error al consultar los datos del empleado", e.getMessage());
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_QUERY_DATA);
    }

  }

  @Override
  public String deleteEmployee(Long id) {
    Optional<Employee> employee = employeeRepository.findById(id);
    if (employee.isPresent()) {
      try {
        employeeRepository.deleteById(id);
        return Constants.MESSAGE_SUCESS_DELETE_EMPLOYEE;
      } catch (Exception e) {
        throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_DELETE_EMPLOYEE);
      }
    } else
      return Constants.MESSAGE_SUCESS_DELETE_EMPLOYEE;

  }
  @Override
  public EmployeeDto getEmployee(Long id) {
    try{
      Optional<Employee> response = employeeRepository.findById(id);
      return response.map(employeeMapper::employeeModelToEmployeeDto).orElse(null);
    } catch (Exception e){
      log.error("Error al consultar los datos del empleado", e.getMessage());
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_QUERY_DATA);
    }

  }
}
