package com.acms.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaController {

    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/sendMessage")
    public void sendMessage(@RequestParam String message){
        this.kafkaTemplate.send("kafkaTopicTest", message);
        log.info(message);
    }

    @KafkaListener(topics="kafkaTopicTest")
    public void listenKafka(String message){
        log.info("Message from Kafka {}", message);
    }
}
