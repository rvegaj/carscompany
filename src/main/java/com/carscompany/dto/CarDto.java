package com.carscompany.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CarDto {

  private Long id;
  private String model;
  private String brand;
  private LocalDate manufacturingDate;

}
