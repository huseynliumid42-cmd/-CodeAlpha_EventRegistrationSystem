package com.codealpha.eventregistrationsystem.repository;

import com.codealpha.eventregistrationsystem.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByUserId(Long userId);

    List<Registration> findByEventId(Long eventId);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    long countByEventId(Long eventId);
}