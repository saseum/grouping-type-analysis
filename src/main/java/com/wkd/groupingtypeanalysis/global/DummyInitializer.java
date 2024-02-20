package com.wkd.groupingtypeanalysis.global;

import com.wkd.groupingtypeanalysis.entity.Record;
import com.wkd.groupingtypeanalysis.entity.Student;
import com.wkd.groupingtypeanalysis.entity.Subject;
import com.wkd.groupingtypeanalysis.repository.RecordRepository;
import com.wkd.groupingtypeanalysis.repository.StudentRepository;
import com.wkd.groupingtypeanalysis.repository.SubjectRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class DummyInitializer {

    private final RecordRepository recordRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @PostConstruct
    public void init() {
        List<Student> allStudents = studentRepository.findAll();
        List<Subject> allSubjects = subjectRepository.findAll();

        log.info("===== Dummy init START =====");
        log.info("===== students Size : " + allStudents.size());
        log.info("===== subjects Size : " + allSubjects.size());

        int range = allSubjects.size();

        Random random = new Random();

        for (Student student : allStudents) {
            Set<Integer> duplicateCheck = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                int prevSize = duplicateCheck.size();
                int idx = random.nextInt(range);
                duplicateCheck.add(idx);
                int currSize = duplicateCheck.size();

                if (prevSize == currSize) {
                    i--;
                    continue;
                }

                recordRepository.save(Record.builder()
                        .student(student)
                        .subject(allSubjects.get(idx))
                        .build());
            }
        }

        log.info("===== Dummy init END =====");
    }
}
