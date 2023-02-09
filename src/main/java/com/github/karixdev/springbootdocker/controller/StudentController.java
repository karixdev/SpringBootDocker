package com.github.karixdev.springbootdocker.controller;

import com.github.karixdev.springbootdocker.dto.StudentRequest;
import com.github.karixdev.springbootdocker.dto.StudentResponse;
import com.github.karixdev.springbootdocker.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @RequestBody StudentRequest payload
    ) {
        return new ResponseEntity<>(
                service.create(payload),
                HttpStatus.CREATED
        );
    }
}
