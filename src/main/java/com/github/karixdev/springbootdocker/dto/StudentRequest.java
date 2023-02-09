package com.github.karixdev.springbootdocker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StudentRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("age")
        Integer age
) {}
