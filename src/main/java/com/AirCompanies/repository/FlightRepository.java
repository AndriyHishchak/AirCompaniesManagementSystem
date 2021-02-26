package com.AirCompanies.repository;

import com.AirCompanies.model.Flight;
import com.AirCompanies.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findByStatus(Status status);
}
