package com.AirCompanies.model;

import com.sun.istack.NotNull;
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
    @NotNull
    @Column(name = "name")
    String name;
    @NotNull
    @Column(name = "type")
    TypeCompany typeCompany;
    @NotNull
    @OneToMany(mappedBy = "airCompany")
    List<Airplane> airplanes;
    @NotNull
    @OneToMany(mappedBy = "airCompany")
    List<Flight> flights;
}
