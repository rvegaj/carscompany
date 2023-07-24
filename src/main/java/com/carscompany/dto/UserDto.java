package com.carscompany.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String password;


}
