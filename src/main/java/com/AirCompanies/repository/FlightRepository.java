package com.AirCompanies.repository;

import com.AirCompanies.model.Country;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByStatus(Status status);
    List<Flight> findByDestinationCountry(Country country);
    List<Flight> findByDepartureCountry(Country country);
    List<Flight> findAllByStatusAndDepartureAtAfter(Status status, LocalDateTime localDateTimeBefore);
    List<Flight> findByDestinationCountryAndDepartureCountry(Country departureCountry, Country destinationCountry);
}
