package com.AirCompanies.Dto;

import com.AirCompanies.model.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightDto {
    long id;
    Status status;
    AirCompany airCompany;
    Airplane airplane;
    Country departureCountry;
    Country destinationCountry;
    long distance;
    LocalTime estimatedFlightTime;
    Date endedAt;
    Date delayStartedAt;

    public static List<FlightDto> fromToFlight (List<Flight> flights) {
        return flights.stream().map(FlightDto::fromFlight).collect(Collectors.toList());
    }

    public static FlightDto fromFlight (Flight flight){
        return FlightDto.builder()
                .id(flight.getId())
                .status(flight.getStatus())
                .airCompany(flight.getAirCompany())
                .airplane(flight.getAirplane())
                .distance(flight.getDistance())
                .estimatedFlightTime(flight.getEstimatedFlightTime())
                .endedAt(flight.getEndedAt())
                .delayStartedAt(flight.getDelayStartedAt())
                .build();
    }
}
