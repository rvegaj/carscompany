package com.carscompany.dto;

import com.carscompany.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
  private Long id;
  private String nameEmployee;
  private String registrationCar;

}
