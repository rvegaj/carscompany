package com.carscompany.service;

import com.carscompany.common.Constants;
import com.carscompany.dao.TripRepository;
import com.carscompany.dto.CarDto;
import com.carscompany.dto.EmployeeDto;
import com.carscompany.dto.TripDto;
import com.carscompany.infraestructure.web.exceptions.ExceptionDataConflict;
import com.carscompany.infraestructure.web.exceptions.ExceptionDataQuery;
import com.carscompany.mapper.TripMapper;
import com.carscompany.model.Trip;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TripServiceImpl implements TripService{

  private final TripRepository tripRepository;

  private final TripMapper tripMapper;
  private final EmployeeService employeeService;
  private final CarService carService;

  public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper,
      EmployeeService employeeService, CarService carService) {
    this.tripRepository = tripRepository;
    this.tripMapper = tripMapper;
    this.carService = carService;
    this.employeeService = employeeService;
  }

  @Override
  public TripDto createTrip(Long employeeId, Long carId) {
    EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
    if (Objects.isNull(employeeDto))
      throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_SAVE_DATA_EMPLOYEE);
    CarDto carDto = carService.getCar(carId);
    if(Objects.isNull(carDto))
      throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_SAVE_DATA_CAR);
    if (findTripsWithCarInUseByEmployee(employeeId,carId))
      throw new ExceptionDataConflict(Constants.MESSAGE_WARNING_SAVE_DATA_CAR);
    return tripMapper.tripModelToTripDto(tripRepository.save(tripMapper.tripDtoToTripModel(builderTripDto(employeeDto,carDto))));
  }

  @Override
  public String deliveryCarByEmployee(Long employeeId, Long carId) {
    validateSaveTrip(employeeId,carId);
      return Constants.MESSAGE_SUCESS_DELIVERY_CAR;
  }
  @Override
  public List<TripDto> findTripsByMonthAndYear(Integer month, Integer year) {
    return tripMapper.listTripModelToListTripDto(tripRepository.findTripsByMonthAndYear(month, year));
  }

  public boolean findTripsWithCarInUseByEmployee(Long employeeId, Long carId) {
    if(tripRepository.findTripsWithCarInUseByEmployee(employeeId, carId)>0)
      return true;
    return false;
  }

  private TripDto builderTripDto(EmployeeDto employeeDto, CarDto carDto){
    return TripDto.builder()
        .employeeId(employeeDto)
        .carId(carDto)
        .retirementDate(LocalDate.now())
        .build();
  }
  public void validateSaveTrip(Long employeeId, Long carId){
    try{
      EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
      if (Objects.isNull(employeeDto))
        throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_SAVE_DATA_EMPLOYEE);
      CarDto carDto = carService.getCar(carId);
      if(Objects.isNull(carDto))
        throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_SAVE_DATA_CAR);
      Optional<Trip> trip = tripRepository.findTripsByEmployeeIdIdAndCarIdId(employeeId,carId);
      if(trip.isPresent()){
        trip.get().setDeliveryDate(LocalDate.now());
        tripRepository.save(trip.get());
      } else
        throw new ExceptionDataConflict(Constants.MESSAGE_ERROR_UPDATE_DATA);
    } catch (Exception e) {
      log.error("Error a devolver el carro", e.getMessage());
      throw new ExceptionDataQuery(Constants.MESSAGE_ERROR_DELIVERY_CAR);
    }
  }
}
