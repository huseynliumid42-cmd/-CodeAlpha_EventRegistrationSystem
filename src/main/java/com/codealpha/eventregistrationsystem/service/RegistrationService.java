package com.codealpha.eventregistrationsystem.service;

import com.codealpha.eventregistrationsystem.entity.Event;
import com.codealpha.eventregistrationsystem.entity.Registration;
import com.codealpha.eventregistrationsystem.entity.User;
import com.codealpha.eventregistrationsystem.repository.EventRepository;
import com.codealpha.eventregistrationsystem.repository.RegistrationRepository;
import com.codealpha.eventregistrationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Registration register(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Eyni user eyni eventə iki dəfə qeydiyyatdan keçə bilməz
        if (registrationRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new RuntimeException("User is already registered for this event");
        }

        // Event dolubsa yeni registration qəbul etmirik
        long registeredCount =
                registrationRepository.countByEventId(eventId);

        if (registeredCount >= event.getCapacity()) {
            throw new RuntimeException("Event is full");
        }

        Registration registration = Registration.builder()
                .user(user)
                .event(event)
                .registeredAt(LocalDateTime.now())
                .build();

        return registrationRepository.save(registration);
    }

    public List<Registration> getUserRegistrations(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        return registrationRepository.findByUserId(userId);
    }

    public List<Registration> getEventRegistrations(Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }

        return registrationRepository.findByEventId(eventId);
    }

    public void cancelRegistration(Long registrationId) {

        Registration registration = registrationRepository
                .findById(registrationId)
                .orElseThrow(() ->
                        new RuntimeException("Registration not found"));

        registrationRepository.delete(registration);
    }
}