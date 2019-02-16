package stp;

import stp.converter.Converter10ToP;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        //Converter10ToP.convert(new BigDecimal("256.00256"))
        BigDecimal v = new BigDecimal("10000001432400");
        System.out.println(v);
    }
}
