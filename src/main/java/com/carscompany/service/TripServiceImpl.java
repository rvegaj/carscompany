package com.carscompany.service;

import com.carscompany.dao.CarRepository;
import com.carscompany.dao.EmployeeRepository;
import com.carscompany.dao.TripRepository;
import com.carscompany.dto.CarDto;
import com.carscompany.dto.EmployeeDto;
import com.carscompany.dto.TripDto;
import com.carscompany.mapper.CarMapper;
import com.carscompany.mapper.EmployeeMapper;
import com.carscompany.mapper.TripMapper;
import com.carscompany.model.Car;
import com.carscompany.model.Employee;
import com.carscompany.model.Trip;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{

  private final TripRepository tripRepository;
  private final TripMapper tripMapper;

  private final EmployeeService employeeService;
  private final CarService carService;

  public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper,
      EmployeeService employeeService, EmployeeMapper employeeMapper, CarService carService,
      CarRepository carRepository, CarMapper carMapper) {
    this.tripRepository = tripRepository;
    this.tripMapper = tripMapper;
    this.carService = carService;
    this.employeeService = employeeService;
  }

  @Override
  public TripDto createTrip(Long employeeId, Long carId) throws Exception {
    EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
    if (Objects.isNull(employeeDto))
      throw new Exception("El empleado a guardar no existe, debe crearlo previamente");
    CarDto carDto = carService.getCar(carId);
    if(Objects.isNull(carDto))
      throw new Exception("El Automovil a guardar no existe, debe crearlo previamente");

    return tripMapper.tripModelToTripDto(tripRepository.save(tripMapper.tripDtoToTripModel(builderTripDto(employeeDto,carDto))));
  }

  @Override
  public String deliveryCarByEmployee(Long employeeId, Long carId) throws Exception {
    try{
      EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
      if (Objects.isNull(employeeDto))
        throw new Exception("El empleado a guardar no existe, debe crearlo previamente");
      CarDto carDto = carService.getCar(carId);
      if(Objects.isNull(carDto))
        throw new Exception("El Automovil a guardar no existe, debe crearlo previamente");
      Optional<Trip> trip = tripRepository.findTripsByEmployeeIdAndCarId(employeeId,carId);
      if(trip.isPresent()){
        trip.get().setDeliveryDate(LocalDate.now());
        tripRepository.save(trip.get());
      } else
        throw new Exception("El registro a actualizar no existe");
    } catch (Exception e) {
      throw new Exception("Error al intentar devolver el automovil");
    }
      return "El automovil fue devuelto exitosamente";
  }

  private TripDto builderTripDto(EmployeeDto employeeDto, CarDto carDto){
    return TripDto.builder()
        .employeeId(employeeDto)
        .carId(carDto)
        .retirementDate(LocalDate.now())
        .build();
  }
}
