package com.carscompany.dto;

import com.carscompany.model.Car;
import com.carscompany.model.Employee;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TripDto {
  private Long id;

  private EmployeeDto employeeId;

  private CarDto carId;

  private LocalDate retirementDate;

  private LocalDate deliveryDate;

}
