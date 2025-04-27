package com.testcodestd.controller;

import com.testcodestd.controller.request.SaveExamScoreRequest;
import com.testcodestd.controller.response.ExamFailStudentResponse;
import com.testcodestd.controller.response.ExamPassStudentResponse;
import com.testcodestd.service.StudentScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScoreApi {

    private final StudentScoreService studentScoreService;
    @PutMapping("/exam/{exam}/score")
    public void save(
            @PathVariable("exam") String exam,
            @RequestBody SaveExamScoreRequest request){
        studentScoreService.saveScore(
                request.getStudentName()
                ,exam
                ,request.getKorScore(),
                request.getEnglishScore(),request.getMatchScore()
        );
    }

    @GetMapping("/exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(
            @PathVariable("exam") String exam
    ){
        return studentScoreService.getPassStudentList(exam);
    }


    @GetMapping("/exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(
            @PathVariable("exam") String exam
    ){
        return studentScoreService.getFailStudentList(exam);
    }

}
