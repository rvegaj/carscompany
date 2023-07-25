package com.carscompany.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TripDto {
  private Long id;
  @NotBlank(message = "El empleado es requerido")
  @NotNull(message = "El empleado es requerido")
  private EmployeeDto employeeId;

  @NotBlank(message = "El automovil es requerido")
  @NotNull(message = "El automovil es requerido")
  private CarDto carId;

  private LocalDate retirementDate;

  private LocalDate deliveryDate;

}
