package com.testcodestd.service;

import com.testcodestd.controller.response.ExamPassStudentResponse;
import com.testcodestd.model.StudentPass;
import com.testcodestd.repository.StudentFailRepositroy;
import com.testcodestd.repository.StudentPassRepository;
import com.testcodestd.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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


    @Test
    @DisplayName("함격자 명단 가져오기 검증")
    public void getPassStudentsListTest() {
        //given

        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepositroy studentFailRepositroy = Mockito.mock(StudentFailRepositroy.class);

        StudentPass expectStudent1 = StudentPass.builder().id(1L).studentName("sohyun").exam("testexam").avgScore(70.0).build();
        StudentPass expectStudent2 =  StudentPass.builder().id(2L).studentName("test").exam("testexam").avgScore(80.0).build();
        StudentPass expectStudent3 =  StudentPass.builder().id(3L).studentName("iamnot").exam("secondExam").avgScore(90.0).build()



        //findAll이 호출되었을때 이리스트를 리턴합니다. when - thenReturn
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                expectStudent1,expectStudent2,expectStudent3
                ));

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepositroy
        );
        String givenTestExam="testExam";

        //when
        var expectResponses =List.of(expectStudent1,expectStudent2)
                .stream()
                .map((pass->new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))).toList();

         List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentList(givenTestExam);
         Assertions.assertIterableEquals(expectResponses,responses);
         responses.forEach((response) -> System.out.println(response.getStudentName()+" "+response.getAvgScore()));


    }
}
