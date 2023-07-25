package com.carscompany.service;

import com.carscompany.dao.CarRepository;
import com.carscompany.dto.CarDto;
import com.carscompany.mapper.CarMapper;
import com.carscompany.model.Car;
import com.carscompany.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

  private final CarRepository carRepository;

  private final CarMapper carMapper;

  public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
    this.carRepository = carRepository;
    this.carMapper = carMapper;
  }
  @Override
  public CarDto createCar(CarDto carDto) {
    return carMapper.carModelToCarDto(carRepository.save(carMapper.carDtoToCarModel(carDto)));
  }
  @Override
  public String deleteCar(Long id) throws Exception {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      try {
        carRepository.deleteById(id);
        return "Automóvil eliminado exitosamente";
      } catch (Exception e) {
        throw new Exception("Error al eliminar al automovil");
      }
    } else
      return "Automóvil a eliminar no existe en la base de datos";
  }

  @Override
  public List<CarDto> getAllCars() {
    return carMapper.listCarModelToListCarDto( (List<Car>)carRepository.findAll());
  }

  @Override
  public CarDto getCar(Long id) {
    Optional<Car> response = carRepository.findById(id);
    return response.map(carMapper::carModelToCarDto).orElse(null);
  }
  @Override
  public List<CarDto> findCarsInUse() {
    return carMapper.listCarModelToListCarDto(carRepository.findCarsInUse());
  }

}
