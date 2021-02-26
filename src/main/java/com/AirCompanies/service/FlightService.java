package com.AirCompanies.service;

import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.Status;

import java.util.List;

public interface FlightService {
    Flight create (Flight flight);
    Flight findById (Long id);
    List<FlightDto> getAll ();
    Flight update (Long id,Flight Flight);
    Flight updateStatus(Long id, String status);
    Flight updateAirplane(Long id, Airplane airplane);
    void deleteFlight (Long id);
    void deleteAllFlight ();

    List<FlightDto> findByFlightToStatus(String status);
}
