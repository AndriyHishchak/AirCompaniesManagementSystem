package com.AirCompanies.service;

import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.model.AirCompany;

import java.util.List;

public interface AirCompanyService {
    AirCompany create (AirCompany AirCompany);
    AirCompany findById (Long id);
    AirCompany findByName (String name);
    List<AirCompanyDto> getAll ();
    AirCompany updateTypeCompany (Long id, String typeCompany);
    AirCompany update (Long id,AirCompany airCompany);
    void deleteAirCompany (Long id);
    void deleteAllAirCompany ();
}
