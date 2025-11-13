package com.api.vehicles.service;

import com.api.vehicles.dto.VehicleDTO;
import com.api.vehicles.entity.Vehicle;
import com.api.vehicles.repository.IVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements IVehicleService {
    private final IVehicleRepository vehicleRepository;

    @Override
    public Mono<VehicleDTO> findById(Long id) {
        return vehicleRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public Flux<VehicleDTO> findAll() {
        return vehicleRepository.findAll()
                .map(this::toDTO);
    }

    @Override
    public Mono<VehicleDTO> saveOrUpdate(VehicleDTO vehicleDTO) {
        return vehicleRepository.save(toEntity(vehicleDTO))
                .map(this::toDTO);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return vehicleRepository.deleteById(id);
    }

    // Métodos de conversão Entity ↔ DTO
    private VehicleDTO toDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getModel(),
                vehicle.getIdBrand()
        );
    }

    private Vehicle toEntity(VehicleDTO dto) {
        return new Vehicle(
                dto.id(),
                dto.model(),
                dto.idBrand()
        );
    }
}