package com.carscompany.infraestructure.web;


import com.carscompany.dto.TripDto;
import com.carscompany.service.TripService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TripController {

  private final TripService tripService;

  public TripController(TripService tripService) {
    this.tripService = tripService;
  }
  @PostMapping(value = "/trips/{employeeId}/{carId}")
  public ResponseEntity<TripDto> createEmployee(@PathVariable Long employeeId, @PathVariable Long carId)
      throws Exception {
    return new ResponseEntity<>(tripService.createTrip(employeeId, carId), HttpStatus.OK);
  }

  @PutMapping(value = "/trips/{employeeId}/{carId}")
  public ResponseEntity<String> deliveryCar(@PathVariable Long employeeId, @PathVariable Long carId)
      throws Exception {
    return new ResponseEntity<>(tripService.deliveryCarByEmployee(employeeId, carId), HttpStatus.OK);
  }

  @GetMapping(value = "/trips/delivery")
  public ResponseEntity<List<TripDto>> findTripsByMonthAndYear(@RequestParam Integer month, @RequestParam Integer year){
    return new ResponseEntity<>(tripService.findTripsByMonthAndYear(month, year), HttpStatus.OK);
  }


}
