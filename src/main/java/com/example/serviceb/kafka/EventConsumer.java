package com.example.serviceb.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    private final String topic;
    private final AtomicReference<String> lastPayload = new AtomicReference<>(null);
    private final AtomicReference<Instant> lastReceivedAt = new AtomicReference<>(null);

    public EventConsumer(@Value("${app.kafka.topic:demo.events}") String topic) {
        this.topic = topic;
    }

    @KafkaListener(topics = "${app.kafka.topic:demo.events}", groupId = "${spring.kafka.consumer.group-id:serviceB}")
    public void onMessage(String payload) {
        lastPayload.set(payload);
        lastReceivedAt.set(Instant.now());
        log.info("Consumed Kafka event from topic={} payload={}", topic, payload);
    }

    public String getLastPayload() {
        return lastPayload.get();
    }

    public Instant getLastReceivedAt() {
        return lastReceivedAt.get();
    }

    public String getTopic() {
        return topic;
    }
}
