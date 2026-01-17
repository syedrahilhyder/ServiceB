package com.example.serviceb.service;

import com.example.serviceb.dto.BResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BService {

    public BResponse getValue(String name) {
        String value = "Hello " + name + " from serviceB";
        return new BResponse("serviceB", value, Instant.now());
    }
}
