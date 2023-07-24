package com.carscompany.dao;


import com.carscompany.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String userName);
}
