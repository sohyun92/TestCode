package com.testcodestd.controller;

import com.testcodestd.controller.request.SaveExamScoreRequest;
import com.testcodestd.controller.response.ExamFailStudentResponse;
import com.testcodestd.controller.response.ExamPassStudentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreApi {

    @PutMapping("/exam/{exam}/score")
    public Object save(
            @PathVariable("exam") String exam,
            @RequestBody SaveExamScoreRequest request){
        return request;
    }

    @GetMapping("/exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(
            @PathVariable("exam") String exam
    )
    {
        return List.of(new ExamPassStudentResponse("sohyeon",60.0));
    }


    @GetMapping("/exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(
            @PathVariable("exam") String exam
    ){
        return List.of(new ExamFailStudentResponse("sohyun",20.0));
    }

}
