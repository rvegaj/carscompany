package com.carscompany.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
  @NotBlank(message = "El nombre del empleado es requerido")
  @NotNull(message = "El nombre del empleado es requerido")
  private String nameEmployee;
  private String registrationCar;

}
