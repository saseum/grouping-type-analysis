package com.ri.groupingtypeanalysis.repository;

import com.ri.groupingtypeanalysis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, String> {
}
