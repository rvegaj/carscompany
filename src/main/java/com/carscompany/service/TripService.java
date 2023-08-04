package com.carscompany.service;

import com.carscompany.dto.ResponseDto;
import com.carscompany.dto.TripDto;
import java.util.List;

public interface TripService {
  TripDto createTrip(Long employeeId, Long carId) throws Exception;
  ResponseDto deliveryCarByEmployee(Long employeeId, Long carId) throws Exception;
  List<TripDto> findTripsByMonthAndYear(Integer month, Integer year);
  List<TripDto> findAll();

}
