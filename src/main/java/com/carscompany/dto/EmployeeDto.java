package com.carscompany.dto;

import com.carscompany.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
  private Long id;
  private String nameEmployee;
  private String registrationCar;

}
