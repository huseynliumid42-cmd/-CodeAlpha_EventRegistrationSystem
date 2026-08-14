package com.codealpha.eventregistrationsystem.controller;

import com.codealpha.eventregistrationsystem.entity.Registration;
import com.codealpha.eventregistrationsystem.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<Registration> register(
            @PathVariable Long eventId,
            @RequestParam Long userId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationService.register(userId, eventId));
    }

    @GetMapping("/users/{userId}/registrations")
    public ResponseEntity<List<Registration>> getUserRegistrations(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                registrationService.getUserRegistrations(userId)
        );
    }

    @GetMapping("/events/{eventId}/registrations")
    public ResponseEntity<List<Registration>> getEventRegistrations(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(
                registrationService.getEventRegistrations(eventId)
        );
    }

    @DeleteMapping("/registrations/{registrationId}")
    public ResponseEntity<Void> cancelRegistration(
            @PathVariable Long registrationId) {

        registrationService.cancelRegistration(registrationId);

        return ResponseEntity.noContent().build();
    }
}