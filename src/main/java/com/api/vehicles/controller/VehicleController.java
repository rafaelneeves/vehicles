package com.api.vehicles.controller;

import com.api.vehicles.dto.VehicleDTO;
import com.api.vehicles.service.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final IVehicleService vehicleService;

    @GetMapping
    public Flux<VehicleDTO> getVehicles() {
        return vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<VehicleDTO>> getVehicleById(@PathVariable Long id) {
        return vehicleService.findById(id)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDto) {
        return vehicleService.saveOrUpdate(vehicleDto);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<VehicleDTO>> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDto) {
        return vehicleService.saveOrUpdate(vehicleDto)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteById(id);
    }
}
