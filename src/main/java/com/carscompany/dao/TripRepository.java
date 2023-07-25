package com.carscompany.dao;

import com.carscompany.model.Trip;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
  Optional<Trip> findTripsByEmployeeIdAndCarId(Long employeeId, Long carId);

}
