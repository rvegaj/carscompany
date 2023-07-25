package com.carscompany.dao;

import com.carscompany.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
  @Query(value = "SELECT c FROM Car c"
              + " INNER JOIN Trip  t"
              + " ON c.id = t.carId.id"
              + " WHERE t.retirementDate IS NOT NULL "
              + " AND t.deliveryDate IS NULL"
  )
  List<Car> findCarsInUse();
}
