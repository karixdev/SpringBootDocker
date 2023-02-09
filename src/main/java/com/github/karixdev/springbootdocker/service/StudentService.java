package com.github.karixdev.springbootdocker.service;

import com.github.karixdev.springbootdocker.dto.StudentRequest;
import com.github.karixdev.springbootdocker.dto.StudentResponse;
import com.github.karixdev.springbootdocker.entity.Student;
import com.github.karixdev.springbootdocker.mapper.StudentResponseMapper;
import com.github.karixdev.springbootdocker.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final StudentResponseMapper mapper;

    @Transactional
    public StudentResponse create(StudentRequest payload) {
        Student student = repository.save(Student.builder()
                .name(payload.name())
                .age(payload.age())
                .build());

        return mapper.map(student);
    }
}
