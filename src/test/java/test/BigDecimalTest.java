package test;


import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void getABS(){
        BigDecimal num1 = new BigDecimal(-2954D);
        BigDecimal abs = num1.abs();
        System.out.println(abs);


    }
}
