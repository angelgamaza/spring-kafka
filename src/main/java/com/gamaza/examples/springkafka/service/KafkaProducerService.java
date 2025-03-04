package com.gamaza.examples.springkafka.service;

import com.gamaza.examples.springkafka.record.ProducerMessage;

/**
 * Service for Kafka Producer
 */
public interface KafkaProducerService {

    /**
     * Send a message to a Kafka Topic and Partition
     *
     * @param topic     The topic to send the message
     * @param partition The partition to send the message
     * @param message   The message to send
     */
    void sendMessage(String topic, Integer partition, ProducerMessage message);

}
