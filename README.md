# Hospital Management System

Simple Spring Boot project with Kafka integration.

## What it does
- Patient management
- Doctor management  
- Appointments
- Kafka events for patient and appointment operations

## How to run
1. Start Kafka on localhost:9092
2. Start PostgreSQL on localhost:5432
3. Run HospitalManagement service (port 8093)
4. Run HospitalwebHook service (port 8094)

## APIs
- Create patient: POST /create
- Get patients: GET /patient
- Create appointment: POST /appointments

## Kafka Topics
- patient-created-topic
- appointment-scheduled-topic

