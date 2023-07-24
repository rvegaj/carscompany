package com.carscompany.service;

import com.carscompany.dao.EmployeeRepository;
import com.carscompany.dto.EmployeeDto;
import com.carscompany.mapper.EmployeeMapper;
import com.carscompany.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    return employeeMapper.employeeModelToEmployeeDto(employeeRepository.save(employeeMapper.employeeDtoToEmployeeModel(employeeDto)));
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    return employeeMapper.listEmployeeModelToListEmployeeDto(
        (List<Employee>) employeeRepository.findAll());
  }

  @Override
  public String deleteEmployee(Long id) throws Exception {
    Optional<Employee> employee = employeeRepository.findById(id);
    if (employee.isPresent()) {
      try {
        employeeRepository.deleteById(id);
        return "Empleado eliminado exitosamente";
      } catch (Exception e) {
        throw new Exception("Error al eliminar al empleado");
      }
    } else
      return "Empleado a eliminar no existe en la base de datos";

  }
}
