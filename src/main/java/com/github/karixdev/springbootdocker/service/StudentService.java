package com.github.karixdev.springbootdocker.service;

import com.github.karixdev.springbootdocker.dto.StudentRequest;
import com.github.karixdev.springbootdocker.dto.StudentResponse;
import com.github.karixdev.springbootdocker.entity.Student;
import com.github.karixdev.springbootdocker.exception.NotFoundException;
import com.github.karixdev.springbootdocker.mapper.StudentResponseMapper;
import com.github.karixdev.springbootdocker.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Page<StudentResponse> getMany(
            Integer page, Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);

        return repository.findAll(pageRequest).map(mapper::map);
    }

    public StudentResponse getById(UUID id) {
        return mapper.map(getByIdOrThrow(id));
    }

    @Transactional
    public StudentResponse update(UUID id, StudentRequest payload) {
        Student student = getByIdOrThrow(id);

        student.setName(payload.name());
        student.setAge(payload.age());

        repository.save(student);

        return mapper.map(student);
    }

    private Student getByIdOrThrow(UUID id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(
                    String.format("Student with id: %s not found", id));
        });
    }
}
