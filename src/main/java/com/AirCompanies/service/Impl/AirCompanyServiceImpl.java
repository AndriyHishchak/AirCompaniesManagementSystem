package com.AirCompanies.service.Impl;


import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.TypeCompany;
import com.AirCompanies.repository.AirCompanyRepository;
import com.AirCompanies.service.AirCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepository airCompanyRepository;

    public AirCompanyServiceImpl(AirCompanyRepository airCompanyRepository) {
        this.airCompanyRepository = airCompanyRepository;
    }


    @Override
    public AirCompany create(AirCompany airCompany) {
        AirCompany airCompanySave = airCompanyRepository.save(airCompany);
        log.info("IN created - airCompany: {} successfully created", airCompanySave);
        return airCompanySave;
    }

    @Override
    public AirCompany findById(Long id) {
        AirCompany airCompany = airCompanyRepository.findById(id).orElse(null);

        if (airCompany == null) {
            log.warn("IN findByID - no airCompany found by id: {}",id);
            return null;
        }
        log.info("IN findByID - airCompany: {} find by id: {}", airCompany,id);
        return airCompany;
    }

    @Override
    public AirCompany findByName(String name) {
        AirCompany airCompany = airCompanyRepository.findByName(name);
        if (airCompany == null) {
            log.warn("IN findByName - no airCompany found by name: {}",name);
            return null;
        }
        log.info("IN findByName - airCompany: {} find by name: {}", airCompany,name);
        return airCompany;
    }

    @Override
    public List<AirCompanyDto> getAll() {
        List<AirCompanyDto> airCompanyDtos = new ArrayList<>();
        List<AirCompany> doctors = airCompanyRepository.findAll();
        doctors.forEach(company -> airCompanyDtos.add(AirCompanyDto.fromAirCompany(company)));
        log.info("IN getAll - {} company found", airCompanyDtos.size());
        return new ArrayList<>(airCompanyDtos);
    }

    @Override
    public AirCompany updateTypeCompany(Long id, String typeCompany) {
        AirCompany airCompany = airCompanyRepository.findById(id).get();
        airCompany.setTypeCompany(TypeCompany.valueOf(typeCompany.toUpperCase()));
        log.info("IN update - AirCompany with id : {} ",id);
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public AirCompany update(Long id,AirCompany airCompanyPath) {

        AirCompany airCompanyRefresh = airCompanyRepository.findById(id).get();
        if(airCompanyPath.getName() !=null){
            airCompanyRefresh.setName(airCompanyPath.getName());
        }
        if(airCompanyPath.getTypeCompany().equals(airCompanyRefresh.getTypeCompany()) ) {
            airCompanyRefresh.setTypeCompany(airCompanyPath.getTypeCompany());
        }

        airCompanyRefresh.setUpdatedAt(new Date());
        log.info("IN update - AirCompany with id : {} ",id);
        return airCompanyRepository.save(airCompanyRefresh);
    }

    @Override
    public void deleteAirCompany(Long id) {
       airCompanyRepository.deleteById(id);
        log.info("IN delete - AirCompany with id : {} ",id);
    }

    @Override
    public void deleteAllAirCompany() {
        airCompanyRepository.deleteAll();
        log.info("IN deleted All AirCompany");
    }
}
