package com.wkd.dev.entity;

import lombok.*;

@ToString
@NoArgsConstructor
@Getter @Setter
public class Student {

    private Long id;

    private String school_number;

    private String stu_name;

    private String gender;

    public Student(String school_number) {
        this.school_number = school_number;
    }

    @Builder
    public Student(String stu_name, String school_number, String gender) {
        this.stu_name = stu_name;
        this.school_number = school_number;
        this.gender = gender;
    }
}
