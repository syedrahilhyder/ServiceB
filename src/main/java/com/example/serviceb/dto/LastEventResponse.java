package com.example.serviceb.dto;

import java.time.Instant;

public record LastEventResponse(
        String topic,
        String payload,
        Instant receivedAt
) {}
