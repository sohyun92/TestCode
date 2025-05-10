package com.testcodestd.service;

import com.testcodestd.repository.StudentFailRepositroy;
import com.testcodestd.repository.StudentPassRepository;
import com.testcodestd.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceMockTest {

    @Test
    @DisplayName("Mock 테스트")
    public void firstsaveSCoreMockTest(){
        //각각의 respository에 Mockito.mock 객체 만들기
        //given
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class),
                Mockito.mock(StudentFailRepositroy.class)
        );

        //when
        String givenStudentName ="sohyun";
        String givenExam = "testExam";
        Integer givenKoreaScore =80;
        Integer givenEnglishScore =100;
        Integer givenMathScore =60;

        studentScoreService.saveScore(
                givenStudentName,givenExam,givenKoreaScore,givenEnglishScore,givenMathScore
        );

    }
}
