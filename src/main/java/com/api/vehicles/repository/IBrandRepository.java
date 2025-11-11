package com.api.vehicles.repository;

import com.api.vehicles.entity.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBrandRepository extends ReactiveCrudRepository<Brand, Long> {
}
