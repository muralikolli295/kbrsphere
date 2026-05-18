package com.kbrsphere.email_service.controller;

import com.kbrsphere.email_service.dto.EmailRequestDTO;
import com.kbrsphere.email_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        emailService.sendWelcomeEmail(request.getTo(),request.getName());
        return ResponseEntity.ok("Email sent successfully");
    }
}
