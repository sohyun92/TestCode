package com.testcodestd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

public class JunitPracticeTest {
    @Test
    public void assertEqualsTest(){
        String expect="somthing";
        String actual="somthing";
        //두값이 동일한지 화인
        Assertions.assertEquals(expect,actual);
    }

    @Test
    public void assertNotEqualsTest(){
        String expect="somthing";
        String actual="hi";
        //두값이 다른지 화인
        Assertions.assertNotEquals(expect,actual);
    }

    @Test
    public void assertTrueTest(){
        Integer a= 10;
        Integer b= 10;
        //조건이 참인지 확인
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    public void assertFalseTest(){
        Integer a= 10;
        Integer b= 12;
        //조건이 거짓인지 확인
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    public void assertThrowsTest(){
        //예외가 발생하는지 확인
        Assertions.assertThrows(RuntimeException.class,()->{
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    public void assertNotnullTest(){
        String value = "hello";
        //값이 null이아닌지 확인
        Assertions.assertNotNull(value);
    }

    @Test
    public void assertNullTest(){
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    public void assertIterableEqualsTest(){
        //두 Iterable 객체가 동일한 요소를 포함하고 있고 순서도 같은지 확인
        List<Integer> list1 = List.of(1,2);
        List<Integer> list2 = List.of(1,2);

        Assertions.assertIterableEquals(list1,list2);
    }

    @Test
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
