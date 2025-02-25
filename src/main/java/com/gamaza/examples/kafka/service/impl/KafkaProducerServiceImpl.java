package com.gamaza.examples.kafka.service.impl;

import com.gamaza.examples.kafka.record.ProducerMessage;
import com.gamaza.examples.kafka.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    // Injection variables
    private final KafkaTemplate<String, ProducerMessage> kafkaTemplate;

    /**
     * Constructor
     */
    public KafkaProducerServiceImpl(KafkaTemplate<String, ProducerMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, Integer partition, ProducerMessage message) {
        logger.debug("Sending message {} to topic: {} and partition: {}", message, topic, partition);
        kafkaTemplate.send(topic, partition, message.id(), message);
        logger.debug("Message {} successfully sent to topic: {} and partition: {}", message, topic, partition);
    }

}
