package com.testcodestd.service;

import com.testcodestd.MyCalculator;
import com.testcodestd.controller.response.ExamFailStudentResponse;
import com.testcodestd.controller.response.ExamPassStudentResponse;
import com.testcodestd.model.StudentFail;
import com.testcodestd.model.StudentPass;
import com.testcodestd.model.StudentScore;
import com.testcodestd.repository.StudentFailRepositroy;
import com.testcodestd.repository.StudentPassRepository;
import com.testcodestd.repository.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepositroy studentFailRepositroy;
    public void saveScore(String studentName, String exam, Integer KorScore,
                          Integer englishScore, Integer mathScore){

        StudentScore studentScore = StudentScore.builder()
                .exam(exam)
                .studentName(studentName)
                .korScore(KorScore)
                .englishScore(englishScore)
                .mathScore(mathScore)
                .build();

        studentScoreRepository.save(studentScore);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator.add(KorScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if(avgScore >= 60){
          StudentPass studentPass = StudentPass.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

        studentPassRepository.save(studentPass);
        }else{
            StudentFail studentFail  = StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

            studentFailRepositroy.save(studentFail);
        }
    }

    public List<ExamPassStudentResponse> getPassStudentList(String exam){
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream().filter((pass)->pass.getExam().equals(exam))
                .map((pass-> new ExamPassStudentResponse(pass.getStudentName(),pass.getAvgScore()))).toList();
    }

    public List<ExamFailStudentResponse> getFailStudentList(String exam){
        List<StudentFail> studentFails = studentFailRepositroy.findAll();

        return studentFails.stream().filter((fail)->fail.getExam().equals(exam))
                .map((fail)->new ExamFailStudentResponse(fail.getStudentName(),fail.getAvgScore())).toList();
    }
}
