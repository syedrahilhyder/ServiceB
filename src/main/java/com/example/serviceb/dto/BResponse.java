package com.example.serviceb.dto;

import java.time.Instant;

public record BResponse(String service, String value, Instant timestamp) {}
