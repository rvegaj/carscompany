package com.carscompany.mapper;

import com.carscompany.dto.EmployeeDto;
import com.carscompany.model.Employee;
import java.util.List;
import lombok.Generated;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Generated
@Mapper
public interface EmployeeMapper {

  EmployeeDto employeeModelToEmployeeDto(Employee employee);
  @InheritInverseConfiguration
  Employee employeeDtoToEmployeeModel(EmployeeDto employeeDto);
  List<EmployeeDto> listEmployeeModelToListEmployeeDto(List<Employee> employeeList);

}
