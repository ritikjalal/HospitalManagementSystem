# Hospital Management System with Kafka Integration

A production-ready hospital management system built with Spring Boot, featuring event-driven architecture using Apache Kafka for real-time processing and webhooks.

## 🏗️ Architecture

### Services
- **Hospital Management Service** (Port 8093): Core hospital operations
- **Hospital Webhook Service** (Port 8094): Event processing and webhook delivery

### Infrastructure
- **Apache Kafka**: Event streaming platform
- **PostgreSQL**: Primary database
- **Prometheus**: Metrics collection
- **Grafana**: Monitoring dashboard

## 🚀 Features

### Core Functionality
- Patient management (CRUD operations)
- Doctor management
- Appointment scheduling
- Billing system
- Real-time event publishing

### Production Features
- **Event-Driven Architecture**: Kafka-based async processing
- **Monitoring**: Prometheus metrics + Grafana dashboards
- **Health Checks**: Actuator endpoints
- **Containerization**: Docker + Docker Compose
- **Logging**: Structured logging with trace IDs
- **Configuration**: Environment-based profiles
- **Webhooks**: External system notifications

## 📋 Prerequisites

- Docker & Docker Compose
- Java 17+ (for local development)
- Maven 3.8+

## 🛠️ Quick Start

### Using Docker Compose (Recommended)

1. Clone and navigate to the project:
```bash
cd HospitalManagementSystem
```

2. Start all services:
```bash
docker-compose up -d
```

3. Verify services are running:
```bash
docker-compose ps
```

### Access Points

- **Hospital Management API**: http://localhost:8093/api
- **Webhook Service**: http://localhost:8094/api
- **Grafana Dashboard**: http://localhost:3000 (admin/admin)
- **Prometheus**: http://localhost:9090

## 📊 Monitoring

### Health Endpoints
- Hospital Management: http://localhost:8093/api/actuator/health
- Webhook Service: http://localhost:8094/api/actuator/health

### Metrics
- Prometheus metrics: `/actuator/prometheus`
- Custom metrics for patient operations, appointments, billing

## 📝 API Examples

### Create Patient
```bash
curl -X POST http://localhost:8093/api/create \
  -H "Content-Type: application/json" \
  -d '{
    "patientName": "John Doe",
    "patientCode": "P001",
    "age": 35,
    "gender": "M"
  }'
```

### Get All Patients
```bash
curl http://localhost:8093/api/patient
```

## 🔄 Event Flow

1. **Patient Operations** → Kafka `patient-events` topic
2. **Appointment Operations** → Kafka `appointment-events` topic
3. **Billing Operations** → Kafka `billing-events` topic
4. **Webhook Service** consumes events and triggers webhooks

