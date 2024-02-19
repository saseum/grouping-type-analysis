package com.ri.groupingtypeanalysis;

import com.ri.groupingtypeanalysis.repository.StudentRepository;
import com.ri.groupingtypeanalysis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupingTypeAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupingTypeAnalysisApplication.class, args);
    }

}
