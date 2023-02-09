package com.github.karixdev.springbootdocker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record StudentResponse(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("age")
        Integer age
) {}
