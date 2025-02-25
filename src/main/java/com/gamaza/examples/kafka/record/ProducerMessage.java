package com.gamaza.examples.kafka.record;

/**
 * Record for Kafka Producer Messages representation
 */
public record ProducerMessage(String id, String message, String description) {
}
