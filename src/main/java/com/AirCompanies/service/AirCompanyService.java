package com.AirCompanies.service;

import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.model.AirCompany;

import java.util.List;
import java.util.Optional;

public interface AirCompanyService {
    AirCompanyDto create (AirCompany AirCompany);
    AirCompanyDto findById (Long id);
    List<AirCompanyDto> getAll(Optional<String> name);
    AirCompanyDto updateTypeCompany (Long id, String typeCompany,Optional<String> name);
//    AirCompanyDto update (Long id,AirCompany airCompany);
    void deleteAirCompany (Long id);
    void deleteAllAirCompany ();
}
