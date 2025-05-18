package com.testcodestd.model;

public class StudentScoreFixture {

    public static StudentScore pass(){
        return StudentScore.builder()
                .exam("defaultExam")
                .studentName("so")
                .korScore(90)
                .englishScore(80)
                .mathScore(100)
                .build();
    }

    public static StudentScore fail(){
        return StudentScore.builder()
                .exam("defaultExam")
                .studentName("so")
                .korScore(40)
                .englishScore(30)
                .mathScore(20)
                .build();
    }


}
