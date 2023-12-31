package com.carscompany.controller;

import com.carscompany.dto.EmployeeDto;
import com.carscompany.service.EmployeeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping(value = "/employees")
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
    return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.OK);
  }

  @GetMapping(value = "/employees")
  public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
    return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/employees/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable  @Valid Long id) throws Exception {
    return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
  }
}
