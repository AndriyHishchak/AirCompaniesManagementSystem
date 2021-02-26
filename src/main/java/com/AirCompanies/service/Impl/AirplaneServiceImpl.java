package com.AirCompanies.service.Impl;


import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.TypeAirplane;
import com.AirCompanies.repository.AirplaneRepository;
import com.AirCompanies.service.AirplaneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Airplane create(Airplane airplane) {
        Airplane airplaneSave = airplaneRepository.save(airplane);
        log.info("IN created - airplane : {} successfully created", airplaneSave);
        return airplaneSave;
    }
    public Airplane findByUuid(String uuid) {
        Airplane airplane = airplaneRepository.findByFactorySerialNumber(uuid);
        log.info("IN findByUuid - airplane: {} find by UUID: {}",airplane,uuid);
        return airplane;
    }
    @Override
    public Airplane findById(Long id) {
        Airplane airplane = airplaneRepository.findById(id).orElse(null);

        if (airplane == null) {
            log.warn("IN findByID - no Airplane found by id: {}",id);
            return null;
        }
        log.info("IN findByID - Airplane: {} find by id: {}", airplane,id);
        return airplane;
    }

    @Override
    public Airplane findByName(String name) {
        Airplane airplane = airplaneRepository.findByName(name);
        if (airplane == null) {
            log.warn("IN findByName - no airplane found by name: {}",name);
            return null;
        }
        log.info("IN findByName - airplane: {} find by name: {}", airplane,name);
        return airplane;
    }



    @Override
    public List<AirplaneDto> getAll() {
        List<AirplaneDto> airplaneDtoList = new ArrayList<>();
        List<Airplane> airplanes = airplaneRepository.findAll();
        airplanes.forEach(airplane -> airplaneDtoList.add(AirplaneDto.fromAirplane(airplane)));
        log.info("IN getAll - {} airplanes found", airplaneDtoList.size());
        return new ArrayList<>(airplaneDtoList);
    }

    @Override
    public Airplane updateTypeAirplane(Long id, String typeAirplane) {
        Airplane airplane = airplaneRepository.findById(id).get();
        airplane.setTypeAirplane(TypeAirplane.valueOf(typeAirplane.toUpperCase()));
        log.info("IN update - AirCompany with id : {} ",id);
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane updateCompany(Long id, AirCompany airCompany) {
        Airplane airplaneUpdate = airplaneRepository.findById(id).get();
        airplaneUpdate.setAirCompany(airCompany);
        airplaneRepository.save(airplaneUpdate);
        return airplaneUpdate;
    }

    @Override
    public Airplane update(Long id,Airplane airplanePath) {

        Airplane airplaneRefresh = airplaneRepository.findById(id).get();
        if(airplanePath.getName() !=null){
            airplaneRefresh.setName(airplanePath.getName());
        }
        if(airplanePath.getTypeAirplane().equals(airplaneRefresh.getTypeAirplane()) ) {
            airplaneRefresh.setTypeAirplane(airplanePath.getTypeAirplane());
        }
        if(airplanePath.getNumberOfFlights() != airplaneRefresh.getNumberOfFlights() ) {
            airplaneRefresh.setNumberOfFlights(airplanePath.getNumberOfFlights());
        }
        if(airplanePath.getFlightDistance() != airplaneRefresh.getFlightDistance() ) {
            airplaneRefresh.setFlightDistance(airplanePath.getFlightDistance());
        }
        if(airplanePath.getFuelCapacity() != airplaneRefresh.getFuelCapacity() ) {
            airplaneRefresh.setFuelCapacity(airplanePath.getFuelCapacity());
        }

        airplaneRefresh.setUpdatedAt(new Date());
        log.info("IN update - Airplane with id : {} ",id);
        return airplaneRepository.save(airplaneRefresh);
    }

    @Override
    public void deleteAirplane(Long id) {
        airplaneRepository.deleteById(id);
        log.info("IN delete - Airplane with id : {} ",id);
    }

    @Override
    public void deleteAllAirplane() {
        airplaneRepository.deleteAll();
        log.info("IN deleted All Airplane");
    }
}
