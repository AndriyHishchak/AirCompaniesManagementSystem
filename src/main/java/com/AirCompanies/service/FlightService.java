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
                                     Optional<Double> distance);
    void deleteFlight (Long id);
    void deleteAllFlight ();

    List<FlightDto> changeFlightStatus();

    List<FlightDto> findByCompletedFlights();
}

