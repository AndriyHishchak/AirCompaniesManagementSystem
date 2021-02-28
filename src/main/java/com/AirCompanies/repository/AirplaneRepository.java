package com.AirCompanies.repository;

import com.AirCompanies.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
    Airplane findByFactorySerialNumber (String uuid);
    List<Airplane> findByName (String name);
}
