package com.carscompany.service;

import com.carscompany.common.Constants;
import com.carscompany.common.validator.ValidationService;
import com.carscompany.dao.CarRepository;
import com.carscompany.dto.CarDto;
import com.carscompany.controller.exceptions.ExceptionDataConflict;
import com.carscompany.controller.exceptions.ExceptionDataQuery;
import com.carscompany.mapper.CarMapper;
import com.carscompany.model.Car;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarServiceImpl implements CarService{

  private final CarRepository carRepository;
  private final CarMapper carMapper;

  private final ValidationService validationService;

  public CarServiceImpl(CarRepository carRepository, CarMapper carMapper,
      ValidationService validationService) {
    this.carRepository = carRepository;
    this.carMapper = carMapper;
    this.validationService = validationService;
  }
  @Override
  public CarDto createCar(CarDto carDto) {
    validationService.validationRequest(carDto);
    return carMapper.carModelToCarDto(carRepository.save(carMapper.carDtoToCarModel(carDto)));
  }
  @Override
  public String deleteCar(Long id) {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      try {
        carRepository.deleteById(id);
        return Constants.MESSAGE_SUCESS_DELETE_CAR;
      } catch (Exception e) {
        log.error("error a borrar automovil", e.getMessage());
        throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_DELETE_CAR);
      }
    } else
      return Constants.MESSAGE_ERROR_DELETE_DATA_CAR;
  }

  @Override
  public List<CarDto> getAllCars() {
    return carMapper.listCarModelToListCarDto( (List<Car>)carRepository.findAll());
  }

  @Override
  public CarDto getCar(Long id) {
    try{
      Optional<Car> response = carRepository.findById(id);
      return response.map(carMapper::carModelToCarDto).orElse(null);
    } catch (Exception e){
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_QUERY_DATA);
    }

  }
  @Override
  public List<CarDto> findCarsInUse() {
    try{
      return carMapper.listCarModelToListCarDto(carRepository.findCarsInUse());
    } catch (Exception e){
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_QUERY_DATA);
    }
  }

}
