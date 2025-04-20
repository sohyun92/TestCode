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
@Table(name ="student_pass")
public class StudentPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_pass_id")
    private Long id;

    @Column(name="exam")
    private Long exam;

    @Column(name="student_name")
    private Long studentName;

    @Column(name="avg_score")
    private Long avgScore;
}
