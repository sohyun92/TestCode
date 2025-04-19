package com.testcodestd;

import org.junit.jupiter.api.*;

import org.springframework.util.Assert;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JunitPracticeTest {
    @Test
    @DisplayName("Assert Equals 메소드")
    public void assert_Equals_Test(){
        String expect="somthing";
        String actual="somthing";
        //두값이 동일한지 화인
        Assertions.assertEquals(expect,actual);
    }

    @Test
    @DisplayName("Assert Not Equals 메소드")
    public void assertNotEqualsTest(){
        String expect="somthing";
        String actual="hi";
        //두값이 다른지 화인
        Assertions.assertNotEquals(expect,actual);
    }

    @Test
    @DisplayName("Assert True 메소드")
    public void assertTrueTest(){
        Integer a= 10;
        Integer b= 10;
        //조건이 참인지 확인
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("Assert False 메소드")
    public void assertFalseTest(){
        Integer a= 10;
        Integer b= 12;
        //조건이 거짓인지 확인
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("Assert Throws 메소드")
    public void assertThrowsTest(){
        //예외가 발생하는지 확인
        Assertions.assertThrows(RuntimeException.class,()->{
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    @DisplayName("Assert Notnull 메소드")
    public void assertNotnullTest(){
        String value = "hello";
        //값이 null이아닌지 확인
        Assertions.assertNotNull(value);
    }

    @Test
    @DisplayName("Assert Null 메소드")
    public void assertNullTest(){
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    @DisplayName("Assert Iterable Equals 메소드")
    public void assertIterableEqualsTest(){
        //두 Iterable 객체가 동일한 요소를 포함하고 있고 순서도 같은지 확인
        List<Integer> list1 = List.of(1,2);
        List<Integer> list2 = List.of(1,2);

        Assertions.assertIterableEquals(list1,list2);
    }

    @Test
    @DisplayName("Assert All 메소드")
    public void assertAllTest(){
        String expect="somthing";
        String actual="somthing";

        List<Integer> list1 = List.of(1,2);
        List<Integer> list2 = List.of(1,2);


        Assertions.assertAll("AssertAll", List.of(
                () -> {Assertions.assertEquals(expect,actual); },
                () -> {Assertions.assertIterableEquals(list1,list2);}
        ));
    }
}
