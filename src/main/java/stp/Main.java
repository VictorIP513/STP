package stp;

import stp.converter.Converter10ToP;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        String s = Converter10ToP.convert(new BigDecimal("254.1021"), 2, 100);
        System.out.println(s);
    }
}
