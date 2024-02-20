package com.wkd.groupingtypeanalysis.repository;

import com.wkd.groupingtypeanalysis.entity.Record;
import com.wkd.groupingtypeanalysis.entity.Student;
import com.wkd.groupingtypeanalysis.entity.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @DisplayName("Record 전체조회 테스트")
    @Transactional
    @Test
    void findAllRecords() {
        List<Record> records = recordRepository.findAll();
        int recordsSize = records.size();

        assertThat(recordsSize).isEqualTo(900);
    }

    @DisplayName("조합 테스트")
    @Transactional
    @Test
    void combinationConditionallyTest() {
        List<Record> records = recordRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();

        String[] examples = new String[] {"화법과작문", "미적분", "물리학2", "여행지리", "논리학", "논술"};

        int groupingCount = 3;

        List<List<String>> result = combinationSubsets(examples.length, groupingCount, examples);

        result.forEach(subList -> {
            System.out.print("( ");

            subList.forEach(v -> System.out.print(v + ", "));

            System.out.println(" )");
        });
    }


    /* combination func (nCm) */
    private List<List<String>> combinationSubsets(int n, int m, String[] subjects) {
        List<List<String>> result = new ArrayList<>();
        backtrack(n, m, 0, subjects, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int n, int m, int start, String[] subjects, List<String> combination, List<List<String>> result) {
        if (combination.size() == m) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < n; i++) {
            combination.add(subjects[i]);
            backtrack(n, m, i + 1, subjects, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
}
