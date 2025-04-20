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
@Table(name ="student_score")
public class StudentScore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_score_id")
    private Long id;

    @Column(name="exam")
    private Long exam;

    @Column(name="student_name")
    private Long studentName;

    @Column(name="kor_score")
    private Integer korScore;

    @Column(name="english_score")
    private Integer englishScore;

    @Column(name="math_score")
    private Integer mathScore;

}
