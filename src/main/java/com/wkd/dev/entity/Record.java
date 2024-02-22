package com.wkd.dev.entity;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class Record {

    private Long id;

    private String school_number;

    private String sbj_name;

    @Builder
    public Record(String school_number, String sbj_name) {
        this.school_number = school_number;
        this.sbj_name = sbj_name;
    }
}
