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
public class CarDto {

  private Long id;
  @NotBlank(message = "El modelo es requerido")
  @NotNull(message = "El modelo es requerido")
  private String model;

  @NotBlank(message = "La marca es requerido")
  @NotNull(message = "La marca es requerido")
  private String brand;

  @NotBlank(message = "La fecha de manufactura es requerida")
  @NotNull(message = "La fecha de manufactura es requerida")
  private LocalDate manufacturingDate;

}
