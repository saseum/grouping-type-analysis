package com.wkd.dev.entity;

import lombok.*;

@ToString
@NoArgsConstructor
@Getter @Setter
public class Subject {

    private Long id;

    private String sbj_name;

    /*
    private String sbj_area;
    private String sbj_group;
    private String sbj_type;
    private int unit;
     */

    public Subject(String sbj_name) {
        this.sbj_name = sbj_name;
    }
}
