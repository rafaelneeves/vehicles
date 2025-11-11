package com.api.vehicles.repository;

import com.api.vehicles.entity.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IVehicleRepository extends ReactiveCrudRepository<Vehicle, Long> {

}
