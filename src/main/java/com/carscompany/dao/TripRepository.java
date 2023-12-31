package com.carscompany.dao;

import com.carscompany.model.Trip;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
  Optional<Trip> findTripsByEmployeeIdIdAndCarIdIdAndDeliveryDateIsNull(Long employeeId, Long carId);

  @Query(value = "SELECT t FROM Trip t "
              + " WHERE t.deliveryDate is not  null "
               + "  AND  EXTRACT( MONTH FROM(t.deliveryDate)) =?1"
                + " AND EXTRACT( YEAR FROM(t.deliveryDate)) =?2")
  List<Trip> findTripsByMonthAndYear(Integer month, Integer year);

  @Query(value = "SELECT count(1) FROM Trip t "
                + "WHERE (t.employeeId.id = ?1 OR "
                + "t.carId.id = ?2 )AND "
                + "t.deliveryDate IS NULL")
  Long findTripsWithCarInUseByEmployee(Long employeeId, Long carId);
}
