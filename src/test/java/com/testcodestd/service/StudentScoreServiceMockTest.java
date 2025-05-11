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

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인경우")
    public void saveScroeMockTest(){
        //given 평균점수가 60점 이상인경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepositroy studentFailRepositroy = Mockito.mock(StudentFailRepositroy.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepositroy
        );

        String givenStudentName ="sohyun";
        String givenExam = "testExam";
        Integer givenKoreaScore =80;
        Integer givenEnglishScore =100;
        Integer givenMathScore =60;
        //when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKoreaScore,
                givenEnglishScore,
                givenMathScore
        );

        //then
        Mockito.verify(studentScoreRepository,Mockito.times(1)).save(Mockito.any()); //1번 실행이 되어야함
        Mockito.verify(studentPassRepository,Mockito.times(1)).save(Mockito.any());  //1번 실행이 되어야함
        Mockito.verify(studentFailRepositroy,Mockito.times(0)).save(Mockito.any()); //0번 실행이 되어야함
    }


    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인경우")
    public void saveScroeMockTest2(){
        //given 평균점수가 60점 이상인경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepositroy studentFailRepositroy = Mockito.mock(StudentFailRepositroy.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepositroy
        );
        String givenStudentName ="sohyun";
        String givenExam = "testExam";
        Integer givenKoreaScore =40;
        Integer givenEnglishScore =40;
        Integer givenMathScore =60;
        //when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKoreaScore,
                givenEnglishScore,
                givenMathScore
        );

        //then
        Mockito.verify(studentScoreRepository,Mockito.times(1)).save(Mockito.any()); //1번 실행이 되어야함
        Mockito.verify(studentPassRepository,Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepositroy,Mockito.times(1)).save(Mockito.any()); //1번 실행이 되어야함
    }
}