package com.freelance.task.freelanceTask.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendRegistrationStatusEmail(String to, String status) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Driver Registration Status");
        message.setText("Dear Driver,\n\nYour registration request is " + status + ".\n\nBest regards,\nYour Team");
        mailSender.send(message);
    }

    public void sendUpdateStatusEmail(String to, String status, String requesterNotes) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Driver Registration Request Update");
        if(requesterNotes.isEmpty()){
            message.setText("Dear Driver,\n\nYour registration request is updated: " + status + ".\n\nBest regards,\nYour Team");
        }else{
            message.setText("Dear Driver,\n\nYour registration request is updated: " + status + ".\n\nRequester Notes Updated:\n" + requesterNotes + ".\n\nBest regards,\nYour Team");
        }
        mailSender.send(message);
    }

    public void sendUpdateStatusEmail(String to, String status) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Driver Registration Update Status");
        message.setText("Dear Driver,\n\nYour registration request is updated: " + status + ".\n\nBest regards,\nYour Team");
        mailSender.send(message);
    }

    public void sendUpdateNotesEmail(String to, String requesterNotes) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Driver Registration Update Requester Notes");
        message.setText("Dear Driver,\n\nYour registration request is updated, Your Requester Notes Updated:\n" + requesterNotes + ".\n\nBest regards,\nYour Team");
        mailSender.send(message);
    }
}
