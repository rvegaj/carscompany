package com.carscompany.mapper;

import com.carscompany.dto.CarDto;
import com.carscompany.dto.EmployeeDto;
import com.carscompany.model.Car;
import com.carscompany.model.Employee;
import java.util.List;
import lombok.Generated;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Generated
@Mapper
public interface CarMapper {

  CarDto carModelToCarDto(Car car);
  @InheritInverseConfiguration
  Car carDtoToCarModel(CarDto carDto);
  List<CarDto> listCarModelToListCarDto(List<Car> carList);

}
