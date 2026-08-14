package com.codealpha.eventregistrationsystem.repository;

import com.codealpha.eventregistrationsystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}