package com.testcodestd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {

    @Test
    public void TestDataTest() {
        TestData testData =new TestData();

        testData.setName("sohyeon");

        Assertions.assertEquals("sohyeon",testData.getName());
    }
}
