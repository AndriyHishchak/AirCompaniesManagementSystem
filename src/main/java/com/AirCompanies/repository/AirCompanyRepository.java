package com.AirCompanies.repository;

import com.AirCompanies.model.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirCompanyRepository extends JpaRepository<AirCompany,Long> {
    List<AirCompany> findByName(String name);
}
