package com.freelance.task.freelanceTask.utilities.scheduler;

import com.freelance.task.freelanceTask.entities.Driver;
import com.freelance.task.freelanceTask.repositories.DriverRepository;
import com.freelance.task.freelanceTask.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class EmailResendScheduledTask {

    private final DriverRepository driverRepository;
    private final EmailService emailService;



    @Scheduled(cron = "0 0 * * * ?")
    public void resendEmailsToDrivers() {
        List<Driver> driversWithPendingEmails = driverRepository.findByEmailSentFalse();
        for (Driver driver : driversWithPendingEmails) {
            try {
                emailService.sendRegistrationStatusEmail(driver.getDriverEmail(), driver.getRequestStatus().toString());
                driver.setEmailSent(true);
                driverRepository.save(driver);
            } catch (Exception e) {
                // Handle the exception, log it, etc.
            }
        }
    }
}