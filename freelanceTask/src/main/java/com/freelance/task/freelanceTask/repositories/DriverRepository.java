package com.freelance.task.freelanceTask.repositories;

import com.freelance.task.freelanceTask.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByEmailSentFalse();
    Optional<Driver> findByDriverEmail(String driverEmail);
    Optional<Driver> findByDriverMobile(String driverMobile);
}
