package com.wkd.groupingtypeanalysis.repository;

import com.wkd.groupingtypeanalysis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, String> {
}
