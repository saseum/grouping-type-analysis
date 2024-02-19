package com.wkd.groupingtypeanalysis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "subject")
@Entity
public class Subject {

    /**
     * 세부교과목(과목명)
     */
    @Id
    private String sbj_name;

    /**
     * 교과영역
     */
    @Column
    private String sbj_area;

    /**
     * 과목군
     * ex) 국어, 수학, 영어, 사회, 과학, 체육, 예술, ...
     */
    @Column
    private String sbj_group;

    /**
     * 과목유형
     * ex) 공통, 일반, 진로
     */
    @Column
    private String sbj_type;

    /**
     * 단위
     */
    @Column
    private int unit;

    @Builder
    public Subject(String sbj_area, String sbj_group, String sbj_type, String sbj_name, int unit) {
        this.sbj_area = sbj_area;
        this.sbj_group = sbj_group;
        this.sbj_type = sbj_type;
        this.sbj_name = sbj_name;
        this.unit = unit;
    }
}
