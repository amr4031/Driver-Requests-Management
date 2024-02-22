package com.freelance.task.freelanceTask.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class CreateDriverDto {
    @NotBlank(message = "Driver name is required")
    private String driverName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Driver email is required")
    private String driverEmail;

    @Pattern(regexp = "[0-9]+", message = "Mobile number should be numeric")
    private String driverMobile;

    private Timestamp videoConferenceMeetingDate;
}
