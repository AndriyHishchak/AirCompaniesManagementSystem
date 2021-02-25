package com.AirCompanies.Dto;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.TypeCompany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirCompanyDto {
    long id;
    String name;
    TypeCompany typeCompany;
    List<AirplaneDto> airplanes;
    List<FlightDto> flights;

    public static AirCompanyDto fromAirCompany (AirCompany airCompany){
        List<FlightDto> flightDtoList =  FlightDto.fromToFlight(airCompany.getFlights());
        List<AirplaneDto> airplaneDtoList =  AirplaneDto.fromToAirplane(airCompany.getAirplanes());

        return AirCompanyDto.builder()
                .id(airCompany.getId())
                .name(airCompany.getName())
                .typeCompany(airCompany.getTypeCompany())
                .airplanes(airplaneDtoList)
                .flights(flightDtoList)
                .build();
    }
}
