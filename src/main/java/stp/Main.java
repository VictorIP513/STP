package stp;

import stp.converter.Converter10ToP;
import stp.converter.ConverterPTo10;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        String s = Converter10ToP.convert("122", 2, 99);
        String s1 = ConverterPTo10.convert("0", 16, 0);
        System.out.println(s);
        System.out.println(s1);
    }
}
