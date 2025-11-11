package com.api.vehicles.service;

import com.api.vehicles.dto.BrandDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BrandServiceImpl implements IBrandService {

    @Override
    public Mono<BrandDTO> findById(Long id) {
        return null;
    }

    @Override
    public Flux<BrandDTO> findAll() {
        return null;
    }

    @Override
    public Mono<BrandDTO> saveOrUpdate(BrandDTO brandDTO) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return null;
    }
}
