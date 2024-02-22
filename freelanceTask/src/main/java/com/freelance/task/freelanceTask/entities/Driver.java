package com.freelance.task.freelanceTask.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DRIVER_ID_SEQ_GEN")
    @SequenceGenerator(name = "DRIVER_ID_SEQ_GEN", sequenceName = "DRIVER_ID_SEQ", allocationSize = 1)
    private Long id;
    @NotBlank(message = "Driver name is required")
    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "request_date")
    private Timestamp requestDate;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Driver email is required")
    @Column(name = "driver_email")
    private String driverEmail;


    @Column(name = "driver_mobile")
    private String driverMobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Column(name = "video_conferenace_meeting_date")
    private Timestamp videoConferenceMeetingDate;

    @Column(name = "notes_to_requester", length = 505)
    private String notesToRequester = "";

    @Column(name = "email_sent")
    private Boolean emailSent = false;

    @PrePersist
    protected void onCreate() {
        requestDate = new Timestamp(System.currentTimeMillis());
        requestStatus = RequestStatus.PENDING;
    }
}
