package com.testcodestd;

public class MyCalculatorApplication
{
    public static void main(String [] args){
        MyCalculator myCalculator = new MyCalculator();

        myCalculator.add(10.0);
        myCalculator.minus(2.0);
        myCalculator.multiply(2.0);


        System.out.println(myCalculator.getResult());
    }
}
