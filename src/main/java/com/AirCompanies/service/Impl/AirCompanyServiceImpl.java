package com.AirCompanies.service.Impl;


import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.TypeCompany;
import com.AirCompanies.repository.AirCompanyRepository;
import com.AirCompanies.service.AirCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepository airCompanyRepository;

    public AirCompanyServiceImpl(AirCompanyRepository airCompanyRepository) {
        this.airCompanyRepository = airCompanyRepository;
    }


    @Override
    public AirCompanyDto create(AirCompany airCompany) {
        List <Airplane> airplanes = new ArrayList<>();
        List <Flight> flights = new ArrayList<>();
        airCompany.setAirplanes(airplanes);
        airCompany.setFlights(flights);
        AirCompany airCompanySave = airCompanyRepository.save(airCompany);
        AirCompanyDto  result = AirCompanyDto.fromAirCompany(airCompanySave);
        log.info("IN created - airCompany: {} successfully created", airCompanySave);
        return result;
    }

    @Override
    public AirCompanyDto findById(Long id) {
        AirCompany airCompany = airCompanyRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Air Company not found"));

        log.info("IN findByID - airCompany: {} find by id: {}", airCompany,id);
        return AirCompanyDto.fromAirCompany(airCompany);
    }



    @Override
    public List<AirCompanyDto> getAll(Optional<String> name) {
        List<AirCompanyDto> airCompanyDtos = new ArrayList<>();

        if (name.isPresent()){
            List<AirCompany> airCompany = Collections.singletonList(airCompanyRepository.findByName(name.get()));
            airCompany.forEach(company -> airCompanyDtos.add(AirCompanyDto.fromAirCompany(company)));
            log.info("IN findByName - airCompany: {} find by name: {}", airCompany,name);
            return airCompanyDtos;
        }

        List<AirCompany> airCompanies = airCompanyRepository.findAll();
        airCompanies.forEach(company -> airCompanyDtos.add(AirCompanyDto.fromAirCompany(company)));
        log.info("IN getAll - {} company found", airCompanyDtos.size());
        return new ArrayList<>(airCompanyDtos);
    }

    @Override
    public AirCompanyDto updateTypeCompany(Long id, String typeCompany) {
        airCompanyRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Air Company not found"));

        AirCompany airCompany = airCompanyRepository.findById(id).get();
        airCompany.setTypeCompany(TypeCompany.valueOf(typeCompany.toUpperCase()));
        airCompanyRepository.save(airCompany);
        log.info("IN update - AirCompany with id : {} ",id);
        return AirCompanyDto.fromAirCompany(airCompany);
    }

    @Override
    public AirCompanyDto update(Long id,AirCompany airCompanyPath) {
        airCompanyRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Air Company not found"));

        AirCompany airCompanyRefresh = airCompanyRepository.findById(id).get();
        if(airCompanyPath.getName() !=null){
            airCompanyRefresh.setName(airCompanyPath.getName());
        }
        if(!airCompanyPath.getTypeCompany().equals(airCompanyRefresh.getTypeCompany()) ) {
            airCompanyRefresh.setTypeCompany(airCompanyPath.getTypeCompany());
        }
        airCompanyRefresh.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));

        airCompanyRepository.save(airCompanyRefresh);


        log.info("IN update - AirCompany with id : {} ",id);
        return AirCompanyDto.fromAirCompany(airCompanyRefresh);
    }

    @Override
    public void deleteAirCompany(Long id) {
        airCompanyRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Air Company not found"));
       airCompanyRepository.deleteById(id);
        log.info("IN delete - AirCompany with id : {} ",id);
    }

    @Override
    public void deleteAllAirCompany() {
        airCompanyRepository.deleteAll();
        log.info("IN deleted All AirCompany");
    }
}
