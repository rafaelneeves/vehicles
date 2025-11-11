package com.api.vehicles.service;

import com.api.vehicles.dto.BrandDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBrandService {
    Mono<BrandDTO> findById(Long id);

    Flux<BrandDTO> findAll();

    Mono<BrandDTO> saveOrUpdate(BrandDTO brandDTO);

    Mono<Void> deleteById(Long id);
}