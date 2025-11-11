package com.api.vehicles.service;

import com.api.vehicles.dto.VehicleDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVehicleService {
    Mono<VehicleDTO> findById(Long id);

    Flux<VehicleDTO> findAll();

    Mono<VehicleDTO> saveOrUpdate(VehicleDTO vehicleDTO);

    Mono<Void> deleteById(Long id);
}