package com.wkd.groupingtypeanalysis.service;

import com.wkd.groupingtypeanalysis.entity.Student;
import com.wkd.groupingtypeanalysis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
