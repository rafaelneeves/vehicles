package com.api.vehicles.service;

import com.api.vehicles.dto.VehicleDTO;
import com.api.vehicles.entity.Vehicle;
import com.api.vehicles.repository.IVehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    void returnVehicleById() {
        Vehicle vehicle = new Vehicle(1L, "Polo", 1L);

        when(vehicleRepository.findById(1L))
                .thenReturn(Mono.just(vehicle));

        StepVerifier.create(vehicleRepository.findById(1L))
                .expectNext(vehicle)
                .verifyComplete();
    }

    @Test
    void notReturnVehicleById() {
        when(vehicleRepository.findById(99L))
                .thenReturn(Mono.empty());

        StepVerifier.create(vehicleRepository.findById(99L))
                .verifyComplete();
    }

    @Test
    void returnsVehiclesSuccessfully() {
        Vehicle vehicle1 = new Vehicle(1L, "Civic", 1L);
        Vehicle vehicle2 = new Vehicle(2L, "Corolla", 2L);

        when(vehicleRepository.findAll())
                .thenReturn(Flux.just(vehicle1, vehicle2));

        StepVerifier.create(vehicleService.findAll())
                .expectNext(new VehicleDTO(1L, "Civic", 1L))
                .expectNext(new VehicleDTO(2L, "Corolla", 2L))
                .verifyComplete();
    }

    @Test
    void NotReturnsVehiclesSuccessfully() {
        when(vehicleRepository.findAll())
                .thenReturn(Flux.empty());

        StepVerifier.create(vehicleService.findAll())
                .verifyComplete();
    }

    @Test
    void saveOrUpdateVehiclesSuccessfully() {
        VehicleDTO inputDTO = new VehicleDTO(1L, "Civic", 1L);
        Vehicle savedVehicle = new Vehicle(1L, "Civic", 1L);
        VehicleDTO expectedDTO = new VehicleDTO(1L, "Civic", 1L);

        when(vehicleRepository.save(any(Vehicle.class)))
                .thenReturn(Mono.just(savedVehicle));

        StepVerifier.create(vehicleService.saveOrUpdate(inputDTO))
                .expectNext(expectedDTO)
                .verifyComplete();
    }

    @Test
    void saveOrUpdateVehiclesFailure() {
        when(vehicleRepository.save(any(Vehicle.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving or updating a vehicle")));

        StepVerifier.create(vehicleService.saveOrUpdate(new VehicleDTO(1L, "Civic", 1L)))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void deleteByIdSuccessfully() {
        when(vehicleRepository.deleteById(1L))
                .thenReturn(Mono.empty());

        StepVerifier.create(vehicleService.deleteById(1L))
                .verifyComplete();
    }

    @Test
    void deleteByIdFailure() {
        when(vehicleRepository.deleteById(1L))
                .thenReturn(Mono.error(new RuntimeException("Error deleting vehicle")));

        StepVerifier.create(vehicleService.deleteById(1L))
                .expectError(RuntimeException.class)
                .verify();
    }
}