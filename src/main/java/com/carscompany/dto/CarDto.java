package com.carscompany.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CarDto {

  private Long id;
  private String model;
  private String brand;
  private LocalDate manufacturingDate;

}
