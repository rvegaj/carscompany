package com.carscompany.service;

import com.carscompany.dao.UserRepository;
import com.carscompany.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  private UserRepository userRepository;

  @Override
  public User getUser(String userName) {
    return userRepository.findByUsername(userName);
  }

  /*@Override
  /*public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }*/
}
