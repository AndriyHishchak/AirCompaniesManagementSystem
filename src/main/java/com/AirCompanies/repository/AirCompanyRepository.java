package com.AirCompanies.repository;

import com.AirCompanies.model.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirCompanyRepository extends JpaRepository<AirCompany,Long> {
}
