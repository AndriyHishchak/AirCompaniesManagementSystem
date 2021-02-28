package com.AirCompanies.service;


import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.TypeAirplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneService {

    AirplaneDto create (Airplane airplane,AirCompany airCompany);
    AirplaneDto findById (Long id);
    List<AirplaneDto> getAll (Optional<String> name,
                              Optional<String> uuid);
    AirplaneDto updateParameterAirplane(Long id,
                                        Optional<AirCompany> airCompany,
                                        Optional<TypeAirplane> typeAirplane,
                                        Optional<String> name,
                                        Optional<Integer> numberOfFlights,
                                        Optional<Double> flightDistance,
                                        Optional<Double> fuelCapacity);
//    AirplaneDto update (Long id,Airplane airCompany);
    void deleteAirplane (Long id);
    void deleteAllAirplane ();
}
