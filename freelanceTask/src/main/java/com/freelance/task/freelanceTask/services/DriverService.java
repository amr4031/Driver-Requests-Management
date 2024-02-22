package com.freelance.task.freelanceTask.services;

import com.freelance.task.freelanceTask.entities.Driver;
import com.freelance.task.freelanceTask.entities.dto.CreateDriverDto;
import com.freelance.task.freelanceTask.repositories.DriverRepository;
import com.freelance.task.freelanceTask.utilities.CustomBusinessException;
//import com.freelance.task.freelanceTask.utilities.mappers.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    private final EmailService emailService;
//    private final DriverMapper driverMapper;

    public Driver createDriver(CreateDriverDto createDriverDto) {
        verifyDriverDoesNotExist(createDriverDto);
        Driver driver = new Driver();
        driver.setDriverName(createDriverDto.getDriverName());
        driver.setDriverEmail(createDriverDto.getDriverEmail());
        driver.setDriverMobile(createDriverDto.getDriverMobile());
        driver.setVideoConferenceMeetingDate(createDriverDto.getVideoConferenceMeetingDate());
        return driverRepository.save(driver);
    }

    private void verifyDriverDoesNotExist(CreateDriverDto createDriverDto) {
        boolean emailExists = driverRepository.findByDriverEmail(createDriverDto.getDriverEmail()).isPresent();
        if (emailExists) {
            throw new CustomBusinessException("Email already exists: " + createDriverDto.getDriverEmail());
        }

        boolean phoneExists = driverRepository.findByDriverMobile(createDriverDto.getDriverMobile()).isPresent();
        if (phoneExists) {
            throw new CustomBusinessException("Phone number already exists: " + createDriverDto.getDriverMobile());
        }
    }

    public Driver findDriverById(Long id) {
        return driverRepository.findById(id).get();
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver updateDriver(Driver updatedDriver) {
        // Fetch existing driver
        Driver existingDriver = driverRepository.findById(updatedDriver.getId())
                .orElseThrow(() -> new CustomBusinessException("Driver not found with ID: " + updatedDriver.getId()));

        // Check if the request status is being updated
        if (!existingDriver.getRequestStatus().equals(updatedDriver.getRequestStatus()) || !existingDriver.getNotesToRequester().equals(updatedDriver.getNotesToRequester())) {
            // Update driver information
            existingDriver.setRequestStatus(updatedDriver.getRequestStatus());
            existingDriver.setNotesToRequester(updatedDriver.getNotesToRequester());

            return driverRepository.save(existingDriver);
        } else {
            // No status change, just return the existing driver
            return existingDriver;
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
