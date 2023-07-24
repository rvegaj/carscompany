package com.carscompany.mapper;

import com.carscompany.dto.UserDto;
import com.carscompany.model.User;
import lombok.Generated;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Generated
@Mapper
public interface UserMapper {

  UserDto userModelToUserDto(User user);

  @InheritInverseConfiguration
  User userDtoToUserModel(UserDto userDto);


}
