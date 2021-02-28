package com.AirCompanies.service;

import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDto create(Flight flight, AirCompany airCompany, Airplane airplane);
    FlightDto findById (Long id);
//    FlightDto update (Long id,Flight Flight);

    List<FlightDto> findByRecentFlights();

    List<FlightDto> getAll(Optional <Status> status,
                           Optional <Country> departureCountry,
                           Optional <Country> destinationCountry,
                           Optional<String> nameCompany);

    FlightDto updateParametersFlight(Long id,
                                     Optional<Status> status,
                                     Optional<AirCompany> airCompany,
                                     Optional<Airplane> airplane,
                                     Optional<Country> departureCountry,
                                     Optional<Country> destinationCountry,
                                     Optional<Double> distance,
                                     Optional<LocalTime> estimatedFlightTime,
                                     Optional<LocalDateTime> endedAt,
                                     Optional<LocalDateTime> StartedAt,
                                     Optional<LocalDateTime> delayStartAt);
    void deleteFlight (Long id);
    void deleteAllFlight ();

    List<FlightDto> changeFlightStatus();

    List<FlightDto> findByCompletedFlights();
}

