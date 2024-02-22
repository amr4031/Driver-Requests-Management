package com.freelance.task.freelanceTask.controllers;


import com.freelance.task.freelanceTask.entities.Driver;
import com.freelance.task.freelanceTask.entities.dto.CreateDriverDto;
import com.freelance.task.freelanceTask.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody CreateDriverDto createDriverDto) {
        return new ResponseEntity<>(driverService.createDriver(createDriverDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDriver() {
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.updateDriver(driver), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return new ResponseEntity<>(driverService.findDriverById(id), HttpStatus.OK);
    }
}
