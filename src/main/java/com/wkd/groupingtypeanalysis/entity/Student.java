package com.wkd.groupingtypeanalysis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter @Setter
@Table(name = "student")
@Entity
public class Student {

    @Id
    private String school_number;

    @Column
    private String stu_name;

    @Column
    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Record> records = new ArrayList<>();

    @Builder
    public Student(String stu_name, String school_number, String gender) {
        this.stu_name = stu_name;
        this.school_number = school_number;
        this.gender = gender;
    }
}
