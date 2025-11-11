package com.api.vehicles.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Vehicles")
public class Vehicle {

    @Id
    private Long id;
    private String brand;
    private Long idBrand;
}
