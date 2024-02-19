package com.ri.groupingtypeanalysis.repository;

import com.ri.groupingtypeanalysis.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
