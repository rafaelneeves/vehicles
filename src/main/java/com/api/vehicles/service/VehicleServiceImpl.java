package com.api.vehicles.service;

import com.api.vehicles.dto.VehicleDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Override
    public Mono<VehicleDTO> findById(Long id) {
        return null;
    }

    @Override
    public Flux<VehicleDTO> findAll() {
        return null;
    }

    @Override
    public Mono<VehicleDTO> saveOrUpdate(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return null;
    }
}
