package com.AirCompanies.repository;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Country;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByStatus(Status status);
    List<Flight> findByAirCompany_Name(String airCompany);

    @Query ( value = "SELECT * FROM Flight " +
                    "WHERE flight.status = 'COMPLETED' " +
                    "And DATEDIFF(flight.ended_at,flight.started_at) > flight.estimated_flight_time" ,nativeQuery = true)
    List<Flight> findByCompletedFlights ();

    List<Flight> findByDestinationCountry(Country country);
    List<Flight> findByDepartureCountry(Country country);
    List<Flight> findByAirCompany_NameAndStatus(String airCompany , Status status);
    List<Flight> findAllByStatusAndStartedAtAfter(Status status, LocalDateTime localDateTimeBefore);
    List<Flight> findByDestinationCountryAndDepartureCountry(Country departureCountry, Country destinationCountry);
}
