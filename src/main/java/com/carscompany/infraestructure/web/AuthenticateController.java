package com.carscompany.infraestructure.web;

import com.carscompany.dto.TokenDto;
import com.carscompany.dto.UserDto;
import com.carscompany.service.LoginService;
import com.carscompany.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthenticateController {

  private final LoginService loginService;

  private final UserService userService;

  public AuthenticateController(LoginService loginService, UserService userService) {
    this.loginService = loginService;
    this.userService = userService;
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<TokenDto> auth(@RequestBody UserDto request) throws Exception {
     return new ResponseEntity<>(loginService.getToken(request), HttpStatus.OK);

  }


}
