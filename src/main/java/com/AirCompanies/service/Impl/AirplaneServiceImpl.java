package com.AirCompanies.service.Impl;


import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.TypeAirplane;
import com.AirCompanies.repository.AirplaneRepository;
import com.AirCompanies.service.AirplaneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public AirplaneDto create(Airplane airplane,AirCompany airCompany) {

        List<Flight> flights = new ArrayList<>();

        airplane.setAirCompany(airCompany);
        airplane.setFlights(flights);
        Airplane airplaneSave = airplaneRepository.save(airplane);
        AirplaneDto result = AirplaneDto.fromAirplane(airplaneSave);
        log.info("IN created - airplane : {} successfully created", result);
        return result;
    }



    @Override
    public AirplaneDto findById(Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Airplane not found"));

        log.info("IN findByID - Airplane: {} find by id: {}", airplane,id);
        return AirplaneDto.fromAirplane(airplane);
    }

    @Override
    public List<AirplaneDto> getAll(Optional<String> name,
                                    Optional<String> uuid) {
        List<AirplaneDto> airplaneDtos = new ArrayList<>();

        if (name.isPresent()){
            List <Airplane> airCompanies = Collections.singletonList(airplaneRepository.findByName( name.get() ));
            airCompanies.forEach(airplane -> airplaneDtos.add(AirplaneDto.fromAirplane(airplane)));
            log.info("IN findByName - airplane: {} find by name: {}", airCompanies,name);
        } else if (uuid.isPresent()) {
            List <Airplane> airCompanies = Collections.singletonList(airplaneRepository.findByFactorySerialNumber( uuid.get() ));
            airCompanies.forEach(airplane -> airplaneDtos.add(AirplaneDto.fromAirplane(airplane)));
            log.info("IN findByFactorySerialNumber - airplane: {} find by SerialNumber: {}", airCompanies,name);
        }
        List<Airplane> airplanes = airplaneRepository.findAll();
        airplanes.forEach(airplane -> airplaneDtos.add(AirplaneDto.fromAirplane(airplane)));
        log.info("IN getAll - {} airplanes found", airplaneDtos.size());
        return new ArrayList<>(airplaneDtos);
    }
    @Override
    public AirplaneDto updateParameterAirplane(Long id,
                                       Optional<AirCompany> airCompany,
                                       Optional<TypeAirplane> typeAirplane,
                                       Optional<String> name,
                                       Optional<Integer> numberOfFlights,
                                       Optional<Double> flightDistance,
                                       Optional<Double> fuelCapacity) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Airplane not found"));
        if (name.isPresent()) {
            airplane.setName(name.get());
            log.info("IN update Name Airplane - Airplane with id : {} to Company  {} ", id, airCompany);}
        if (airCompany.isPresent()) {
            airplane.setAirCompany(airCompany.get());
            log.info("IN update AirCompany Airplane - Airplane with id : {} ", id);}
        if ( typeAirplane.isPresent()) {
            airplane.setTypeAirplane(typeAirplane.get());
            log.info("IN update Type Airplane Airplane - Airplane with id : {} ", id);}
        if (numberOfFlights.isPresent()) {
            airplane.setNumberOfFlights(numberOfFlights.get());
            log.info("IN update Number Of Flights Airplane - Airplane with id : {} ", id);}
        if (flightDistance.isPresent()) {
            airplane.setFlightDistance(flightDistance.get());
            log.info("IN update Flight Distance Airplane - Airplane with id : {} ", id);}
        if (fuelCapacity.isPresent()) {
            airplane.setFuelCapacity(fuelCapacity.get());
            log.info("IN update Fuel Capacity Airplane - Airplane with id : {} ", id);}
        log.info("IN finish update - Airplane with id : {} ",id);
        return AirplaneDto.fromAirplane(airplaneRepository.save(airplane));
    }

    @Override
    public AirplaneDto update(Long id,Airplane airplanePath) {
        Airplane airplaneRefresh = airplaneRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Airplane not found"));

        if(airplanePath.getName() !=null){
            airplaneRefresh.setName(airplanePath.getName());
        }
        if(!airplanePath.getTypeAirplane().equals(airplaneRefresh.getTypeAirplane()) ) {
            airplaneRefresh.setTypeAirplane(airplanePath.getTypeAirplane());
        }
        if(!airplanePath.getNumberOfFlights().equals(airplaneRefresh.getNumberOfFlights())) {
            airplaneRefresh.setNumberOfFlights(airplanePath.getNumberOfFlights());
        }
        if(!airplanePath.getFlightDistance().equals(airplaneRefresh.getFlightDistance())) {
            airplaneRefresh.setFlightDistance(airplanePath.getFlightDistance());
        }
        if(!airplanePath.getFuelCapacity().equals(airplaneRefresh.getFuelCapacity())) {
            airplaneRefresh.setFuelCapacity(airplanePath.getFuelCapacity());
        }
        airplaneRefresh.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        airplaneRepository.save(airplaneRefresh);
        log.info("IN update - Airplane with id : {} ",id);
        return AirplaneDto.fromAirplane(airplaneRefresh);
    }

    @Override
    public void deleteAirplane(Long id) {
        airplaneRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Airplane not found"));
        airplaneRepository.deleteById(id);
        log.info("IN delete - Airplane with id : {} ",id);
    }

    @Override
    public void deleteAllAirplane() {
        airplaneRepository.deleteAll();
        log.info("IN deleted All Airplane");
    }

}
