package com.AirCompanies.service.Impl;

import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.model.*;
import com.AirCompanies.repository.FlightRepository;
import com.AirCompanies.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight create(Flight flight) {
        Flight FlightSave = flightRepository.save(flight);
        log.info("IN created - flight : {} successfully created", FlightSave);
        return FlightSave;
    }

    @Override
    public Flight findById(Long id) {
        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight == null) {
            log.warn("IN findByID - no flight found by id: {}",id);
            return null;
        }
        log.info("IN findByID - flight: {} find by id: {}", flight,id);
        return flight;
    }



    @Override
    public List<FlightDto> getAll() {
        List<FlightDto> flightDtos = new ArrayList<>();
        List<Flight> flights = flightRepository.findAll();
        flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
        log.info("IN getAll - {} flights found", flightDtos.size());
        return new ArrayList<>(flightDtos);
    }

    @Override
    public Flight updateStatus(Long id, String status) {
        Flight flight = flightRepository.findById(id).get();
        flight.setStatus(Status.valueOf(status.toUpperCase()));
        log.info("IN update status - flight with id : {} ",id);
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateAirplane(Long id, Airplane airplane) {
        Flight flightUpdate = flightRepository.findById(id).get();
        flightUpdate.setAirplane(airplane);
        flightUpdate.setAirCompany(airplane.getAirCompany());
        flightRepository.save(flightUpdate);
        return flightUpdate;
    }

    @Override
    public Flight update (Long id, Flight flightPath) {

        Flight flightRefresh = flightRepository.findById(id).get();

        if(!flightPath.getStatus().equals(flightRefresh.getStatus()) ) {
            flightRefresh.setStatus(flightPath.getStatus());
        }
        if(!flightPath.getDepartureCountry().equals(flightRefresh.getDepartureCountry()) ) {
            flightRefresh.setDepartureCountry(flightPath.getDepartureCountry());
        }
        if(!flightPath.getDestinationCountry().equals(flightRefresh.getDestinationCountry()) ) {
            flightRefresh.setDestinationCountry((flightPath.getDestinationCountry()));
        }
        if(flightPath.getDistance() != (flightRefresh.getDistance()) ) {
            flightRefresh.setDistance(flightPath.getDistance());
        }
        if(flightPath.getEstimatedFlightTime() != (flightRefresh.getEstimatedFlightTime()) ) {
            flightRefresh.setEstimatedFlightTime(flightPath.getEstimatedFlightTime());
        }
        if(flightPath.getDepartureAt() != (flightRefresh.getDepartureAt()) ) {
            flightRefresh.setDepartureAt(flightPath.getDepartureAt());
        }
        if(flightPath.getEndedAt() != (flightRefresh.getEndedAt()) ) {
            flightRefresh.setEndedAt(flightPath.getEndedAt());
        }

        flightRefresh.setUpdatedAt(new Date());
        log.info("IN update - flight with id : {} ",id);
        return flightRepository.save(flightRefresh);
    }
    @Override
    public List<FlightDto> findByFlightToStatus(String status) {
        List<FlightDto> flightDtos = new ArrayList<>();
        List<Flight> flights = flightRepository.findAll().stream().filter( flight ->
                flight.getStatus().equals(Status.valueOf(status.toUpperCase()))).collect(Collectors.toList());
        flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
        log.info("IN getAll - {} flight found", flightDtos.size());
        return flightDtos;
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
        log.info("IN delete - Flight with id : {} ",id);
    }

    @Override
    public void deleteAllFlight() {
        flightRepository.deleteAll();
        log.info("IN deleted All Flight");
    }


}
