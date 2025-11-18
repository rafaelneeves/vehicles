package com.api.vehicles.service;

import com.api.vehicles.dto.BrandDTO;
import com.api.vehicles.entity.Brand;
import com.api.vehicles.repository.IBrandRepository;
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
class BrandServiceImplTest {

    @Mock
    private IBrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void returnBrandById() {
        Brand brand = new Brand(1L, "Honda");

        when(brandRepository.findById(1L))
                .thenReturn(Mono.just(brand));

        StepVerifier.create(brandRepository.findById(1L))
                .expectNext(brand)
                .verifyComplete();
    }

    @Test
    void notReturnBrandById() {
        when(brandRepository.findById(99L))
                .thenReturn(Mono.empty());

        StepVerifier.create(brandRepository.findById(99L))
                .verifyComplete();
    }

    @Test
    void returnsBrandsSuccessfully() {
        Brand brand1 = new Brand(1L, "Honda");
        Brand brand2 = new Brand(2L, "Toyota");

        when(brandRepository.findAll())
                .thenReturn(Flux.just(brand1, brand2));

        StepVerifier.create(brandService.findAll())
                .expectNext(new BrandDTO(1L, "Honda"))
                .expectNext(new BrandDTO(2L, "Toyota"))
                .verifyComplete();
    }

    @Test
    void NotReturnsBrandsSuccessfully() {
        when(brandRepository.findAll())
                .thenReturn(Flux.empty());

        StepVerifier.create(brandService.findAll())
                .verifyComplete();
    }

    @Test
    void saveOrUpdateBrandsSuccessfully() {
        BrandDTO inputDTO = new BrandDTO(1L, "Honda");
        Brand savedBrand = new Brand(1L, "Honda");
        BrandDTO expectedDTO = new BrandDTO(1L, "Honda");

        when(brandRepository.save(any(Brand.class)))
                .thenReturn(Mono.just(savedBrand));

        StepVerifier.create(brandService.saveOrUpdate(inputDTO))
                .expectNext(expectedDTO)
                .verifyComplete();
    }

    @Test
    void saveOrUpdateBrandsFailure() {
        when(brandRepository.save(any(Brand.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving or updating a brand")));

        StepVerifier.create(brandService.saveOrUpdate(new BrandDTO(1L, "Honda")))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void deleteByIdSuccessfully() {
        when(brandRepository.deleteById(1L))
                .thenReturn(Mono.empty());

        StepVerifier.create(brandService.deleteById(1L))
                .verifyComplete();
    }

    @Test
    void deleteByIdFailure() {
        when(brandRepository.deleteById(1L))
                .thenReturn(Mono.error(new RuntimeException("Error deleting brand")));

        StepVerifier.create(brandService.deleteById(1L))
                .expectError(RuntimeException.class)
                .verify();
    }
}

