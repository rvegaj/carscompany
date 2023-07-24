package com.carscompany.config;

import com.carscompany.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailConfig {
  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return username -> {
      var user = userService.getUser(username);

      return User
          .withUsername(username)
          .password(user.getPassword())
          .roles(user.getRol())
          .build();

    };
  }

}
