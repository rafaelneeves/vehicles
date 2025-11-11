package com.api.vehicles.controller;

import com.api.vehicles.dto.BrandDTO;
import com.api.vehicles.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final IBrandService brandService;

    @GetMapping
    public Flux<BrandDTO> getBrands() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BrandDTO>> getBrandById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(brand -> ResponseEntity.ok().body(brand))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BrandDTO> saveBrand(@RequestBody BrandDTO brandDto) {
        return brandService.saveOrUpdate(brandDto);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BrandDTO>> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDto) {
        return brandService.saveOrUpdate(brandDto)
                .map(brand -> ResponseEntity.ok().body(brand))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBrand(@PathVariable Long id) {
        return brandService.deleteById(id);
    }
}
