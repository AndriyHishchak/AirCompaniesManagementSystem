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
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDto create(Flight flight,AirCompany airCompany,Airplane airplane) {
        flight.setStatus(Status.PENDING);
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
                                  Optional<Country> destinationCountry,
                                  Optional<String> nameCompany) {
        List<FlightDto> flightDtos = new ArrayList<>();

        if (status.isPresent() && nameCompany.isPresent()) {
            List<Flight> flights = flightRepository.findByAirCompany_NameAndStatus(nameCompany.get(),status.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByAirCompany_NameAndStatus - flight: {} find by Status flight and Company: {}",status,nameCompany);
            return new ArrayList<>(flightDtos);
        }
        if (status.isPresent()) {
            List<Flight> flights = flightRepository.findByStatus(status.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByStatus - flight: {} find by status: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }
        if (departureCountry.isPresent() && destinationCountry.isPresent()){
            List<Flight> flights = flightRepository.findByDestinationCountryAndDepartureCountry(departureCountry.get(),destinationCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDestinationCountryAndDepartureCountry - flight: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }
        if (nameCompany.isPresent()) {
            List<Flight> flights = flightRepository.findByAirCompany_Name(nameCompany.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByAirCompany_Name - flight: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }
        if (departureCountry.isPresent()) {
            List<Flight> flights = flightRepository.findByDepartureCountry(departureCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDepartureCountry - flight: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }
        if (destinationCountry.isPresent()) {
            List<Flight> flights = flightRepository.findByDestinationCountry(destinationCountry.get());
            flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
            log.info("IN findByDestinationCountry - flight: {} find by Country: {}",flights,status);
            return new ArrayList<>(flightDtos);
        }

        List<Flight> flightDtoList = flightRepository.findAll();
        flightDtoList.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
        log.info("IN getAll - {} flight found", flightDtos.size());
        return new ArrayList<>(flightDtos);

    }

    @Override
    public FlightDto updateParametersFlight(Long id,
                                         Optional<Status> status,
                                         Optional<AirCompany> airCompany,
                                         Optional<Airplane> airplane,
                                         Optional<Country> departureCountry,
                                         Optional<Country> destinationCountry,
                                         Optional<Double> distance
                                         ) {

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

        flight.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        log.info("IN finish update  - flight with id : {} ",id);
        return FlightDto.fromFlight(flightRepository.save(flight));
    }


    @Override
    public List<FlightDto> changeFlightStatus() {

        List<Flight> flights = flightRepository.findAll();

        flights.forEach(flight -> {

            if (flight.getStatus().equals(Status.PENDING)) {

                flight.setDelayStartAt(flight.getCreatedAt()); }

            if (flight.getStatus().equals(Status.ACTIVE)) {

                flight.setStartedAt(flight.getCreatedAt().plusHours(1)); }

            if (flight.getStatus().equals(Status.COMPLETED)) {
                flight.setEndedAt(flight.getStartedAt()
                        .plusHours(flight.getEstimatedFlightTime().getHour())
                        .plusMinutes(flight.getEstimatedFlightTime().getMinute())
                        .plusSeconds(flight.getEstimatedFlightTime().getSecond()));
            }
            flight.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        });
        return FlightDto.fromToFlight(flightRepository.saveAll(flights));
    }

    @Override
    public List<FlightDto> findByCompletedFlights() {
        List<FlightDto> flightDtos = new ArrayList<>();
        List<Flight> flights = flightRepository.findByCompletedFlights();
        flights.forEach(flight -> flightDtos.add(FlightDto.fromFlight(flight)));
        log.info("IN getAll - {} flight found", flightDtos.size());
        return new ArrayList<>(flightDtos);
    }

    @Override
    public List<FlightDto> findByRecentFlights() {
        List<FlightDto> flightDtos = new ArrayList<>();

        LocalDateTime yesterday = LocalDateTime.now(Clock.systemDefaultZone()).minusDays(1);
        List<Flight> flights = flightRepository.findAllByStatusAndStartedAtAfter(Status.ACTIVE,yesterday);

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
