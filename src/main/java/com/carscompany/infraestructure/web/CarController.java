package com.carscompany.infraestructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class CarController {



  /*
  @GetMapping(value = "/users")
  public ResponseEntity<List<User>> getAllUsers(){
    userService.getAllUsers();
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  } */

}
