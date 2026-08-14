package com.codealpha.eventregistrationsystem.service;

import com.codealpha.eventregistrationsystem.entity.Event;
import com.codealpha.eventregistrationsystem.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event updateEvent(Long id, Event newEvent) {

        Event event = getEventById(id);

        event.setTitle(newEvent.getTitle());
        event.setDescription(newEvent.getDescription());
        event.setLocation(newEvent.getLocation());
        event.setEventDate(newEvent.getEventDate());
        event.setCapacity(newEvent.getCapacity());

        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}