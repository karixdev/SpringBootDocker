package com.github.karixdev.springbootdocker.controller;

import com.github.karixdev.springbootdocker.dto.StudentRequest;
import com.github.karixdev.springbootdocker.dto.StudentResponse;
import com.github.karixdev.springbootdocker.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getMany(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return new ResponseEntity<>(
                service.getMany(page, size),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(
            @PathVariable(name = "id") UUID id
    ) {
        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }
}
