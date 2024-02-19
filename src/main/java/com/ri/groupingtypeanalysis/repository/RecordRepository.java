package com.ri.groupingtypeanalysis.repository;

import com.ri.groupingtypeanalysis.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
