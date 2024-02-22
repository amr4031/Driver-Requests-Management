package com.freelance.task.freelanceTask.aop;

import com.freelance.task.freelanceTask.entities.Driver;
import com.freelance.task.freelanceTask.repositories.DriverRepository;
import com.freelance.task.freelanceTask.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class UpdateStatusEmailNotificationAspect {

    private final EmailService emailService;
    private final DriverRepository driverRepository;


    @Async
    @AfterReturning(value = "execution(* com.freelance.task.freelanceTask.services.DriverService.updateDriver(..))", returning = "driver")
    public void afterDriverCreation(Driver driver) {
        emailService.sendUpdateStatusEmail(driver.getDriverEmail(), driver.getRequestStatus().toString(),driver.getNotesToRequester());

        driver.setEmailSent(true);
        driverRepository.save(driver);
    }
}