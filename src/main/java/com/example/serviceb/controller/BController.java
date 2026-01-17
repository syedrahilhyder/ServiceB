package com.example.serviceb.controller;

import com.example.serviceb.dto.BResponse;
import com.example.serviceb.dto.LastEventResponse;
import com.example.serviceb.kafka.EventConsumer;
import com.example.serviceb.service.BService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b")
public class BController {

    private final BService bService;
    private final EventConsumer eventConsumer;

    public BController(BService bService, EventConsumer eventConsumer) {
        this.bService = bService;
        this.eventConsumer = eventConsumer;
    }

    /**
     * Simple API that Service A calls.
     * Example: GET /api/b/value?name=Rahil
     */
    @GetMapping("/value")
    public BResponse value(@RequestParam(defaultValue = "friend") String name) {
        return bService.getValue(name);
    }

    /**
     * Shows the last Kafka message consumed by serviceB.
     */
    @GetMapping("/last-event")
    public LastEventResponse lastEvent() {
        return new LastEventResponse(
                eventConsumer.getTopic(),
                eventConsumer.getLastPayload(),
                eventConsumer.getLastReceivedAt()
        );
    }
}
