package com.AirCompanies.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "airCompany")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId=true)
public class AirCompany extends BaseEntity {
    @NotNull
    @Column(name = "name")
    String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    TypeCompany typeCompany;
    @NotNull
    @OneToMany(mappedBy = "airCompany",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    List<Airplane> airplanes;
    @NotNull
    @OneToMany(mappedBy = "airCompany",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    List<Flight> flights;

    @Override
    public String toString() {
        return "AirCompany{" +
                "name='" + name + '\'' +
                ", typeCompany=" + typeCompany +
                '}';
    }
}
