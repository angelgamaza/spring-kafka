package com.gamaza.examples.kafka.controller;

import com.gamaza.examples.kafka.dto.StandardResponseDto;
import com.gamaza.examples.kafka.service.KafkaConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Kafka Consumer operations
 */
@RestController
@RequestMapping(value = "/v1/kafka/consumer")
public class KafkaConsumerController {

    // Injection variables
    private final KafkaConsumerService service;

    /**
     * Constructor
     */
    public KafkaConsumerController(KafkaConsumerService service) {
        this.service = service;
    }

    @PostMapping(value = "/start")
    public ResponseEntity<StandardResponseDto> start(@RequestParam String listenerId) {
        return ResponseEntity.ok(
                service.start(listenerId)
        );
    }

    @PostMapping(value = "/stop")
    public ResponseEntity<StandardResponseDto> stop(@RequestParam String listenerId) {
        return ResponseEntity.ok(
                service.stop(listenerId)
        );
    }

}
