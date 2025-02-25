package com.gamaza.examples.kafka.service.impl;

import com.gamaza.examples.kafka.dto.StandardResponseDto;
import com.gamaza.examples.kafka.exception.GenericRuntimeException;
import com.gamaza.examples.kafka.record.ProducerMessage;
import com.gamaza.examples.kafka.service.KafkaConsumerService;
import com.gamaza.examples.kafka.util.ExceptionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    // Injection variables
    private final KafkaListenerEndpointRegistry registry;

    /**
     * Constructor
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public KafkaConsumerServiceImpl(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @Override
    public StandardResponseDto start(String listenerId) {
        try {
            Objects.requireNonNull(registry.getListenerContainer(listenerId)).start();
        } catch (Exception e) {
            // Error messages
            String exceptionMessage = ExceptionUtils.getCauseOrDefaultMessage(e);
            String errorDescription = String.format("There was an error starting the listener with ID [%s], cause was: %s", listenerId, exceptionMessage);
            // Logs
            logger.error(errorDescription);
            // Throw the exception
            throw new GenericRuntimeException(errorDescription);
        }
        return StandardResponseDto.of(
                String.format("Listener with ID [%s] started successfully", listenerId)
        );
    }

    @Override
    public StandardResponseDto stop(String listenerId) {
        try {
            Objects.requireNonNull(registry.getListenerContainer(listenerId)).stop();
        } catch (Exception e) {
            // Error messages
            String exceptionMessage = ExceptionUtils.getCauseOrDefaultMessage(e);
            String errorDescription = String.format("There was an error stopping the listener with ID [%s], cause was: %s", listenerId, exceptionMessage);
            // Logs
            logger.error(errorDescription);
            // Throw the exception
            throw new GenericRuntimeException(errorDescription);
        }
        return StandardResponseDto.of(
                String.format("Listener with ID [%s] stopped successfully", listenerId)
        );
    }

    /**
     * Listeners
     */

    @KafkaListener(
            id = "${application.kafka.listener.topic1.id}",
            topics = "${application.kafka.listener.topic1.topic}",
            groupId = "${application.kafka.listener.topic1.groupId}"
    )
    public void topic1Listener(ConsumerRecord<String, ProducerMessage> message) {
        logger.info(
                "Received message in topic [{}] with data: Partition: [{}], Offset: [{}], Key: [{}], Value: [{}]",
                message.topic(),
                message.partition(),
                message.offset(),
                message.key(),
                message.value()
        );
    }

    @KafkaListener(
            id = "${application.kafka.listener.topic2.id}",
            topics = "${application.kafka.listener.topic2.topic}",
            groupId = "${application.kafka.listener.topic2.groupId}"
    )
    public void topic2Listener(ConsumerRecord<String, ProducerMessage> message) {
        logger.info(
                "Received message in topic [{}] with data: Partition: [{}], Offset: [{}], Key: [{}], Value: [{}]",
                message.topic(),
                message.partition(),
                message.offset(),
                message.key(),
                message.value()
        );
    }
}
