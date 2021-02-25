package com.AirCompanies.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "airCompany")
public class AirCompany extends BaseEntity {
    @Column(name = "name")
    String name;
    @Column(name = "type")
    Type type;
    @OneToMany(mappedBy = "airCompany")
    List<Airplane> airplanes;
    @OneToMany(mappedBy = "airCompany")
    List<Flight> flights;
}
