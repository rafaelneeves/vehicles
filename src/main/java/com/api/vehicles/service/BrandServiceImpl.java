package com.api.vehicles.service;

import com.api.vehicles.dto.BrandDTO;
import com.api.vehicles.entity.Brand;
import com.api.vehicles.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements IBrandService {
    private final IBrandRepository brandRepository;

    @Override
    public Mono<BrandDTO> findById(Long id) {
        return brandRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public Flux<BrandDTO> findAll() {
        return brandRepository.findAll()
                .map(this::toDTO);
    }

    @Override
    public Mono<BrandDTO> saveOrUpdate(BrandDTO brandDTO) {
        return brandRepository.save(toEntity(brandDTO))
                .map(this::toDTO);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return brandRepository.deleteById(id);
    }

    // Métodos de conversão Entity ↔ DTO
    private BrandDTO toDTO(Brand brand) {
        return new BrandDTO(
            brand.getId(),
            brand.getName()
        );
    }

    private Brand toEntity(BrandDTO dto) {
        return new Brand(
            dto.id(),
            dto.name()
        );
    }
}
