# Event Registration System

A RESTful backend application built with **Java, Spring Boot, and PostgreSQL** for the CodeAlpha Backend Development Internship.

## Features

* Create, view, update, and delete events
* Create and view users
* Register users for events
* Prevent duplicate registrations
* Event capacity control
* View user and event registrations
* Cancel registrations

## Technologies

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Gradle
* Lombok
* REST API
* Postman

## Main API Endpoints

* `POST /api/users` — Create user
* `GET /api/events` — View events
* `POST /api/events` — Create event
* `GET /api/events/{id}` — View event details
* `POST /api/events/{eventId}/register?userId={userId}` — Register for an event
* `GET /api/users/{userId}/registrations` — View user registrations
* `DELETE /api/registrations/{id}` — Cancel registration

**
CodeAlpha Backend Development Internship
