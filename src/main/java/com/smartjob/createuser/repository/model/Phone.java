package com.smartjob.createuser.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;

}
