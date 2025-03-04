package com.gamaza.examples.springkafka.service;

import com.gamaza.examples.springkafka.dto.StandardResponseDto;

/**
 * Service for Kafka Consumer
 */
public interface KafkaConsumerService {

    /**
     * Start the Kafka Listener with the given ID
     *
     * @param listenerId The Kafka Listener ID
     * @return The result of the operation
     */
    StandardResponseDto start(String listenerId);

    /**
     * Stop the Kafka Listener with the given ID
     *
     * @param listenerId The Kafka Listener ID
     * @return The result of the operation
     */
    StandardResponseDto stop(String listenerId);

}
