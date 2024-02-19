package com.wkd.groupingtypeanalysis.repository;

import com.wkd.groupingtypeanalysis.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
