package com.AirCompanies.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt"},
        allowGetters = true
)
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId=true)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
