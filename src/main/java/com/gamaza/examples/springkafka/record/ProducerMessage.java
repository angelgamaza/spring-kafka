package com.gamaza.examples.springkafka.record;

/**
 * Record for Kafka Producer Messages representation
 */
public record ProducerMessage(String id, String message, String description) {
}
