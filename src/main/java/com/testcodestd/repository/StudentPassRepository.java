package com.testcodestd.repository;

import com.testcodestd.model.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPassRepository extends JpaRepository<StudentPass,Long> {
}
