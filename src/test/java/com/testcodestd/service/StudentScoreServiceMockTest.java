package com.testcodestd.service;

import com.testcodestd.MyCalculator;
import com.testcodestd.controller.response.ExamPassStudentResponse;
import com.testcodestd.model.StudentFail;
import com.testcodestd.model.StudentPass;
import com.testcodestd.model.StudentScore;
import com.testcodestd.model.StudentScoreTestDataBuilder;
import com.testcodestd.repository.StudentFailRepositroy;
import com.testcodestd.repository.StudentPassRepository;
import com.testcodestd.repository.StudentScoreRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class StudentScoreServiceMockTest {

    private  StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepositroy studentFailRepositroy;

    @BeforeEach
    public void beforeEach(){
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        studentPassRepository = Mockito.mock(StudentPassRepository.class);
        studentFailRepositroy = Mockito.mock(StudentFailRepositroy.class);
        studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepositroy
        );
    }

    @Test
    @DisplayName("Mock 테스트")
    public void firstsaveSCoreMockTest(){
        //각각의 respository에 Mockito.mock 객체 만들기
        //given


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



        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed().build();

        StudentPass expectStudentPass = StudentPass.builder()
                .studentName(expectStudentScore.getStudentName())
                .exam(expectStudentScore.getExam())
                .avgScore((new MyCalculator(0.0)).add(expectStudentScore.getKorScore().doubleValue())
                        .add(expectStudentScore.getEnglishScore().doubleValue())
                        .add(expectStudentScore.getMathScore().doubleValue())
                        .divide(3.0)
                        .getResult()
                ).build();

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        //then
        Mockito.verify(studentScoreRepository,Mockito.times(1)).save(studentScoreArgumentCaptor.capture()); //1번 실행이 되어야함
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();

        Assertions.assertEquals(expectStudentScore.getStudentName(),capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(),capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(),capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(),capturedStudentScore.getMathScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(),capturedStudentScore.getEnglishScore());


        Mockito.verify(studentPassRepository,Mockito.times(1)).save(studentPassArgumentCaptor.capture());  //1번 실행이 되어야함
        StudentPass capturedStudentPass = studentPassArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentPass.getStudentName(),capturedStudentPass.getStudentName());
        Assertions.assertEquals(expectStudentPass.getExam(),capturedStudentPass.getExam());
        Assertions.assertEquals(expectStudentPass.getAvgScore(),capturedStudentPass.getAvgScore());


        Mockito.verify(studentFailRepositroy,Mockito.times(0)).save(Mockito.any()); //0번 실행이 되어야함
    }


    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인경우")
    public void saveScroeMockTest2(){
        //given 평균점수가 60점 이상인경우
        String givenStudentName ="sohyun";
        String givenExam = "testExam";
        Integer givenKoreaScore =40;
        Integer givenEnglishScore =40;
        Integer givenMathScore =60;

        StudentScore expectStudentScore = StudentScore.builder().studentName(givenStudentName)
                .exam(givenExam)
                .korScore(givenKoreaScore)
                .englishScore(givenEnglishScore)
                .mathScore(givenMathScore)
                .build();

        StudentFail expectStudentFail =  StudentFail.builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .avgScore(
                        (new MyCalculator(0.0)).add(givenKoreaScore.doubleValue())
                                .add(givenEnglishScore.doubleValue())
                                .add(givenMathScore.doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();

        ArgumentCaptor<StudentScore> studentScoreArgument = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail.class);


        //when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKoreaScore,
                givenEnglishScore,
                givenMathScore
        );

        //then
        Mockito.verify(studentScoreRepository,Mockito.times(1)).save(studentScoreArgument.capture()); //1번 실행이 되어야함
        StudentScore capturedStudentScore = studentScoreArgument.getValue();

        Assertions.assertEquals(expectStudentScore.getStudentName(),capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(),capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(),capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(),capturedStudentScore.getMathScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(),capturedStudentScore.getEnglishScore());



        Mockito.verify(studentPassRepository,Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepositroy,Mockito.times(1)).save(studentFailArgumentCaptor.capture()); //1번 실행이 되어야함
    }


    @Test
    @DisplayName("함격자 명단 가져오기 검증")
    public void getPassStudentsListTest() {
        //given


        StudentPass expectStudent1 = StudentPass.builder().id(1L).studentName("sohyun").exam("testexam").avgScore(70.0).build();
        StudentPass expectStudent2 =  StudentPass.builder().id(2L).studentName("test").exam("testexam").avgScore(80.0).build();
        StudentPass expectStudent3 =  StudentPass.builder().id(3L).studentName("iamnot").exam("secondExam").avgScore(90.0).build();



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
