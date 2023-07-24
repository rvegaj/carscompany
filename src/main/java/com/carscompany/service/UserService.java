package com.carscompany.service;


import com.carscompany.model.User;
import java.util.List;

public interface UserService {

  User getUser(String userName);

  List<User> getAllUsers();

}
