package com.carscompany.service;

import com.carscompany.dto.CarDto;
import java.util.List;

public interface CarService {
  CarDto createCar(CarDto carDto);
  String deleteCar(Long id) throws Exception;
  List<CarDto> getAllCars();
  CarDto getCar(Long id);
  List<CarDto> findCarsInUse();
}
