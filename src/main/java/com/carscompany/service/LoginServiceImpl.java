package com.carscompany.service;

import com.carscompany.common.Constants;
import com.carscompany.config.util.JwtUtils;
import com.carscompany.dto.TokenDto;
import com.carscompany.dto.UserDto;
import com.carscompany.infraestructure.web.exceptions.ExceptionInvalidCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private UserDetailsService userDetailsService;
  @Override
  public TokenDto getToken(UserDto request) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
      final String token = jwtUtils.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
      return new TokenDto(token);
    } catch (AuthenticationException e) {
      throw new ExceptionInvalidCredentials(Constants.MESSAGE_INVALID_CREDENTIALS);
    }


  }


}
