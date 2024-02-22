package com.wkd.dev.mapper;

import com.wkd.dev.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordMapper {

    List<Record> selectAllRecords();

    void insertRecord(Record record);

    int getCountByContitionalGroup(Map<String, Object> sbjGroup);
}
