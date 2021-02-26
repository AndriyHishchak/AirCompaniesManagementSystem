package com.AirCompanies.service;


import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;

import java.util.List;

public interface AirplaneService {

    Airplane create (Airplane airplane);
    Airplane findById (Long id);
    Airplane findByName (String name);
    Airplane findByUuid (String name);
    List<AirplaneDto> getAll ();
    Airplane updateTypeAirplane (Long id, String typeAirplane);
    Airplane updateCompany (Long id, AirCompany airCompany);
    Airplane update (Long id,Airplane airCompany);
    void deleteAirplane (Long id);
    void deleteAllAirplane ();
}
