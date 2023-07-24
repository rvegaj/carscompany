package com.carscompany.infraestructure.web;

import com.carscompany.model.User;
import com.carscompany.service.LoginService;
import com.carscompany.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {

  private final UserService userService;

  public EmployeeController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/users")
  public ResponseEntity<List<User>> getAllUsers(){
    userService.getAllUsers();
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

}
