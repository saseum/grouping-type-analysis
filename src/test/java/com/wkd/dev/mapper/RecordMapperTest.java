package com.wkd.dev.mapper;

import com.wkd.dev.entity.Record;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository 테스트 - Record")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class RecordMapperTest {

    @Autowired
    private RecordMapper recordMapper;

    @DisplayName("모든 레코드 조회 테스트")
    @Test
    void selectAllRecordsTest() {
        List<Record> all = recordMapper.selectAllRecords();

        assertThat(all).isNotEmpty().hasSize(2151);
    }

    @DisplayName("레코드 단건 DB 입력 테스트")
    @Transactional
    @Test
    void insertRecordTest() {

        List<Record> before = recordMapper.selectAllRecords();
        int beforeSize = before.size();

        Record record = Record.builder()
                .sbj_name("윤리와사상")
                .school_number("30825")
                .build();

        recordMapper.insertRecord(record);

        List<Record> after = recordMapper.selectAllRecords();
        int afterSize = after.size();

        assertThat(beforeSize).isNotEqualTo(afterSize);
    }

    @DisplayName("교과목 조합 그룹별 수강학생 수를 DB 조회 후 출력한다.")
    @Test
    void printCountByContitionalSubjectGroup() {
        String[] selectedSubjects = new String[] {"화법과 작문", "영미문학읽기", "한국지리", "사회문화", "물리학Ⅱ", "화학Ⅱ"};

        List<List<String>> combList = combinationSubsets(selectedSubjects.length, 3, selectedSubjects);

        List<Map<String, Object>> countList = new ArrayList<>();

        combList.forEach(subList -> {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("sbjGroup", subList);
            paramMap.put("count", subList.size());
            int resultCount = recordMapper.getCountByContitionalGroup(paramMap);

            countList.add(Map.of("sbjGroup", subList, "count", resultCount));
        });

        countList.sort(
                Comparator.comparingInt((Map<String, Object> m) -> (Integer) m.get("count")
                ).reversed()
        );

        for(Map<String, Object> map : countList) {
            List<String> sbjGroup = (List<String>) map.get("sbjGroup");
            int count = (Integer) map.get("count");

            if(count > 0) System.out.println(sbjGroup.toString() + " = " + count);
        }
    }


    // nCm Combination Function
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
