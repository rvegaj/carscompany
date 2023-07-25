package com.carscompany.service;

import com.carscompany.dto.TripDto;

public interface TripService {
  TripDto createTrip(Long employeeId, Long carId) throws Exception;
  String deliveryCarByEmployee(Long employeeId, Long carId) throws Exception;

}
