package com.gamaza.examples.springkafka.controller;

import com.gamaza.examples.springkafka.dto.StandardResponseDto;
import com.gamaza.examples.springkafka.record.ProducerMessage;
import com.gamaza.examples.springkafka.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Kafka Producer operations
 */
@RestController
@RequestMapping(value = "/v1/kafka/producer")
public class KafkaProducerController {

    // Injection variables
    private final KafkaProducerService service;

    /**
     * Constructor
     */
    public KafkaProducerController(KafkaProducerService service) {
        this.service = service;
    }

    @PostMapping(value = "/publish/{topic}/partition/{partition}")
    public ResponseEntity<StandardResponseDto> sendMessage(@PathVariable String topic, @PathVariable Integer partition, @RequestBody ProducerMessage message) {
        service.sendMessage(topic, partition, message);
        return ResponseEntity.ok(
                StandardResponseDto.of(
                        String.format("Message successfully sent to topic: [%s] and partition: [%s]", topic, partition)
                )
        );
    }

}
