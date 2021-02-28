package com.AirCompanies.service.Impl;

import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.*;
import com.AirCompanies.repository.FlightRepository;
import com.AirCompanies.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDto create(Flight flight,AirCompany airCompany,Airplane airplane) {
        flight.setAirCompany(airCompany);
        flight.setAirplane(airplane);
        Flight FlightSave = flightRepository.save(flight);
        FlightDto result = FlightDto.fromFlight(FlightSave);
        log.info("IN created - flight : {} successfully created", FlightSave);
        return result;
    }

    @Override
    public FlightDto findById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Flight not found"));

        log.info("IN findByID - flight: {} find by id: {}", flight,id);
        return FlightDto.fromFlight(flight);
    }

    public List<FlightDto> getAll(Optional<Status> status,
                                  Optional<Country> departureCountry,
                                  Optional<Country> destinationCountry) {
        List<FlightDto> flightDtos = new ArrayList<>();

        if (status.isPresent()) {
            List<Flight> flights = flightRepository.findByStatus(status.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByStatus - flight: {} find by status: {}",flights,status);
        return new ArrayList<>(flightDtos);
        } else if (departureCountry.isPresent() && destinationCountry.isPresent()){
            List<Flight> flights = flightRepository.findByDestinationCountryAndDepartureCountry(departureCountry.get(),destinationCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDestinationCountryAndDepartureCountry - airCompany: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        } else if (departureCountry.isPresent()) {
            List<Flight> flights = flightRepository.findByDepartureCountry(departureCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDepartureCountry - airCompany: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        } else if (destinationCountry.isPresent()) {
            List<Flight> flights = flightRepository.findByDestinationCountry(destinationCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDestinationCountry - airCompany: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }
        List<Flight> flightDtoList = flightRepository.findAll();
        flightDtoList.forEach(company -> flightDtos.add(FlightDto.fromFlight(company)));
        log.info("IN getAll - {} company found", flightDtos.size());
        return new ArrayList<>(flightDtos);

    }

    @Override
    public FlightDto updateParametersFlight(Long id,
                                         Optional<Status> status,
                                         Optional<AirCompany> airCompany,
                                         Optional<Airplane> airplane,
                                         Optional<Country> departureCountry,
                                         Optional<Country> destinationCountry,
                                         Optional<Double> distance,
                                         Optional<LocalTime> estimatedFlightTime,
                                         Optional<LocalDateTime> endedAt,
                                         Optional<LocalDateTime> departureAt,
                                         Optional<LocalTime> delayStartAt) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Flight not found"));
        if (status.isPresent()) {
            flight.setStatus(status.get());
            log.info("IN update status Flight - Flight with id : {} ", id);
        }
        if (airCompany.isPresent()){
            flight.setAirCompany(airCompany.get());
            log.info("IN update airCompany Flight - Flight with id : {} ", id);
        }
        if(airplane.isPresent()) {
            flight.setAirplane(airplane.get());
            log.info("IN update airplane Flight - Flight with id : {} ", id);
        }
        if (departureCountry.isPresent()) {
            flight.setDepartureCountry(departureCountry.get());
            log.info("IN update departureCountry Flight - Flight with id : {} ", id);
        }
        if (destinationCountry.isPresent()) {
            flight.setDestinationCountry(destinationCountry.get());
            log.info("IN update destinationCountry Flight - Flight with id : {} ", id);
        }
        if (distance.isPresent()) {
            flight.setDistance(distance.get());
            log.info("IN update distance Flight - Flight with id : {} ", id);
        }
        if (estimatedFlightTime.isPresent()) {
            flight.setEstimatedFlightTime(estimatedFlightTime.get());
            log.info("IN update estimatedFlightTime Flight - Flight with id : {} ", id);
        }
        if (endedAt.isPresent()) {
            flight.setEndedAt(endedAt.get());
            log.info("IN update endedAt Flight - Flight with id : {} ", id);
        }
        if (departureAt.isPresent()) {
            flight.setDepartureAt(departureAt.get());
            log.info("IN update departureAt Flight - Flight with id : {} ", id);
        }
        if (delayStartAt.isPresent()) {
            flight.setDelayStartAt(delayStartAt.get());
            log.info("IN update delayStartAt Flight - Flight with id : {} ", id);
        }
        flight.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        log.info("IN finish update  - flight with id : {} ",id);
        return FlightDto.fromFlight(flightRepository.save(flight));
    }

//    @Override
//    public FlightDto update (Long id, Flight flightPath) {
//        Flight flightRefresh = flightRepository.findById(id)
//                .orElseThrow( () -> new NotFoundException("Flight not found"));
//
//        if(!flightPath.getStatus().equals(flightRefresh.getStatus()) ) {
//            flightRefresh.setStatus(flightPath.getStatus());
//        }
//        if(!flightPath.getDepartureCountry().equals(flightRefresh.getDepartureCountry()) ) {
//            flightRefresh.setDepartureCountry(flightPath.getDepartureCountry());
//        }
//        if(!flightPath.getDestinationCountry().equals(flightRefresh.getDestinationCountry()) ) {
//            flightRefresh.setDestinationCountry((flightPath.getDestinationCountry()));
//        }
//        if(!flightPath.getDistance().equals(flightRefresh.getDistance())) {
//            flightRefresh.setDistance(flightPath.getDistance());
//        }
//        if(flightPath.getEstimatedFlightTime() != (flightRefresh.getEstimatedFlightTime()) ) {
//            flightRefresh.setEstimatedFlightTime(flightPath.getEstimatedFlightTime());
//        }
//        if(flightPath.getDepartureAt() != (flightRefresh.getDepartureAt()) ) {
//            flightRefresh.setDepartureAt(flightPath.getDepartureAt());
//        }
//        if(flightPath.getEndedAt() != (flightRefresh.getEndedAt()) ) {
//            flightRefresh.setEndedAt(flightPath.getEndedAt());
//        }
//
//        flightRefresh.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
//
//        log.info("IN update - flight with id : {} ",id);
//        return FlightDto.fromFlight(flightRepository.save(flightRefresh));
//    }


    @Override
    public List<FlightDto> findByRecentFlights() {
        List<FlightDto> flightDtos = new ArrayList<>();

        LocalDateTime yesterday = LocalDateTime.now(Clock.systemDefaultZone()).minusDays(1);
        List<Flight> flights = flightRepository.findAllByStatusAndDepartureAtAfter(Status.ACTIVE,yesterday);

        flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
        log.info("IN getAll - {} flight found at before {}", flightDtos.size(),yesterday);
        return flightDtos;
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Flight not found"));

        flightRepository.deleteById(id);
        log.info("IN delete - Flight with id : {} ",id);
    }

    @Override
    public void deleteAllFlight() {
        flightRepository.deleteAll();
        log.info("IN deleted All Flight");
    }


}
