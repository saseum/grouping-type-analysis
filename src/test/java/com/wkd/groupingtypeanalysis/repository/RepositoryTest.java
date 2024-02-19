package com.wkd.groupingtypeanalysis.repository;

import com.wkd.groupingtypeanalysis.entity.Record;
import com.wkd.groupingtypeanalysis.entity.Student;
import com.wkd.groupingtypeanalysis.entity.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository 테스트")
@SpringBootTest
class RepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private RecordRepository recordRepository;

    @DisplayName("학생 전체 조회 테스트")
    @Test
    void selectAllStudent() {
        List<Student> all = studentRepository.findAll();

        assertThat(all).isNotNull().hasSize(239);
    }

    @DisplayName("학생 추가 테스트")
    @Test
    void insertNewStudent() {
        Student newStudent = Student.builder()
                .stu_name("Sam")
                .gender("M")
                .school_number("12345")
                .build();

        Student savedStudent = studentRepository.save(newStudent);

        assertThat(savedStudent).isNotNull().hasFieldOrPropertyWithValue("stu_name", "Sam");
    }

    @DisplayName("선택과목과 함께 학생 추가 테스트")
    @Test
    void insertNewStudentWithSubjects() {
        Student student = Student.builder()
                .stu_name("Sam")
                .gender("M")
                .school_number("12345")
                .build();

        Student savedStudent = studentRepository.save(student);

        List<Subject> allSubjects = subjectRepository.findAll();

        for (int i = 0; i < 3; i++) {
            recordRepository.save(Record.builder()
                    .student(savedStudent)
                    .subject(allSubjects.get(i))
                    .build()
            );
        }

        List<Record> allRecords = recordRepository.findAll();

        assertThat(allRecords).isNotNull().hasSize(3);
    }

}