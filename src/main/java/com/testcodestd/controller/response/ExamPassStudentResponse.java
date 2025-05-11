package com.testcodestd.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class ExamPassStudentResponse {
    private final String studentName;
    private final Double avgScore;
}
