package com.testcodestd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="student_fail")
public class StudentFail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_fail_id")
    private Long id;

    @Column(name="exam")
    private String exam;

    @Column(name="student_name")
    private String studentName;


    @Column(name="avg_score")
    private Double avgScore;
}
