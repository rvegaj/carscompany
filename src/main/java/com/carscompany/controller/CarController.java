package com.carscompany.controller;

import com.carscompany.dto.CarDto;
import com.carscompany.service.CarService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CarController {

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping(value = "/cars")
  public ResponseEntity<CarDto> createEmployee(@RequestBody CarDto carDto){
    return new ResponseEntity<>(carService.createCar(carDto), HttpStatus.OK);
  }

  @GetMapping(value = "/cars")
  public ResponseEntity<List<CarDto>> getAllCars(){
    return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
  }
  @DeleteMapping(value = "/cars/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws Exception {
    return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
  }

  @GetMapping(value = "/cars/retirement")
  public ResponseEntity<List<CarDto>> getCarsRetirementDate(){
    return new ResponseEntity<>(carService.findCarsInUse(), HttpStatus.OK);
  }

}
