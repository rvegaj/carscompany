package com.carscompany.service;

import com.carscompany.dto.TokenDto;
import com.carscompany.dto.UserDto;

public interface LoginService {
  TokenDto getToken(UserDto request) throws Exception;

}
