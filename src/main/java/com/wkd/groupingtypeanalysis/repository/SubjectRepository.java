package com.wkd.groupingtypeanalysis.repository;

import com.wkd.groupingtypeanalysis.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
