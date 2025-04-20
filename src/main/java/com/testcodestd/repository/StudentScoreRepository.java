package com.testcodestd.repository;

import com.testcodestd.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore,Long> {


}
