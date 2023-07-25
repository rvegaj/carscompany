package com.carscompany.mapper;

import com.carscompany.dto.TripDto;
import com.carscompany.model.Trip;
import java.util.List;
import lombok.Generated;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Generated
@Mapper
public interface TripMapper {

  TripDto tripModelToTripDto(Trip trip);
  @InheritInverseConfiguration
  Trip tripDtoToTripModel(TripDto tripDto);
  List<TripDto> listTripModelToListTripDto(List<Trip> tripList);

}
