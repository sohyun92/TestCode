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
    private Long exam;

    @Column(name="student_name")
    private Long studentName;


    @Column(name="avg_score")
    private Long avgScore;
}
