package com.github.karixdev.springbootdocker.mapper;

import com.github.karixdev.springbootdocker.dto.StudentResponse;
import com.github.karixdev.springbootdocker.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentResponseMapper implements Mapper<Student, StudentResponse> {
    @Override
    public StudentResponse map(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getAge());
    }

    @Override
    public List<StudentResponse> map(List<Student> students) {
        return students.stream().map(this::map).toList();
    }
}
