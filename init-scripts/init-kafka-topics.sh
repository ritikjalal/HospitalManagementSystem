#!/bin/bash

# Wait for Kafka to be ready
echo "Waiting for Kafka to be ready..."
while ! nc -z kafka 9092; do
  sleep 1
done
echo "Kafka is ready!"

# Create topics
echo "Creating Kafka topics..."

kafka-topics --create --if-not-exists --bootstrap-server kafka:29092 --topic patient-events --partitions 3 --replication-factor 1
kafka-topics --create --if-not-exists --bootstrap-server kafka:29092 --topic appointment-events --partitions 3 --replication-factor 1
kafka-topics --create --if-not-exists --bootstrap-server kafka:29092 --topic billing-events --partitions 3 --replication-factor 1

echo "Kafka topics created successfully!"
