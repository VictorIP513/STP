package stp.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Converter10ToP {

    private static final Map<Integer, Character> DIGITS;
    private static final int MAXBASE = 16;
    private static final int MINBASE = 2;
    private static final int MAXPRECISION = 100;

    static {
        DIGITS = new HashMap<>();
        DIGITS.put(0, '0');
        DIGITS.put(1, '1');
        DIGITS.put(2, '2');
        DIGITS.put(3, '3');
        DIGITS.put(4, '4');
        DIGITS.put(5, '5');
        DIGITS.put(6, '6');
        DIGITS.put(7, '7');
        DIGITS.put(8, '8');
        DIGITS.put(9, '9');
        DIGITS.put(10, 'A');
        DIGITS.put(11, 'B');
        DIGITS.put(12, 'C');
        DIGITS.put(13, 'D');
        DIGITS.put(14, 'E');
        DIGITS.put(15, 'F');
    }

    private Converter10ToP() {

    }

    public static String convert(BigDecimal value, int base, int precision) {
        if (base < MINBASE || base > MAXBASE) {
            throw new IllegalArgumentException("Base must be from " + MINBASE + " to " + MAXBASE);
        }
        if (precision < 0 || precision > MAXPRECISION) {
            throw new IllegalArgumentException("Precision must be from 0 to " + MAXPRECISION);
        }
        value = value.stripTrailingZeros();
        BigInteger num = value.toBigInteger();
        StringBuilder intResult = convertBigInteger(num, base);

        if (value.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO) || precision == 0) {
            return intResult.toString();
        }

        BigInteger fraction = new BigInteger(value.toString().split("\\.")[1]);
        StringBuilder fractionResult = convertBigInteger(fraction, base);
        fractionResult.reverse();
        fractionResult.append(zerosAfterComma(value.toString()));
        fractionResult.reverse();
        intResult.append(".");
        if (fractionResult.length() > precision) {
            fractionResult.delete(precision, fractionResult.length());
        } else {
            addZerosToFractionPart(fractionResult, precision);
        }
        intResult.append(fractionResult);
        return intResult.toString();
    }

    private static String zerosAfterComma(String value) {
        value = value.split("\\.")[1];
        StringBuilder zeros = new StringBuilder();
        for (char ch : value.toCharArray()) {
            if (ch != '0') {
                break;
            }
            zeros.append("0");
        }
        return zeros.toString();
    }

    private static StringBuilder addZerosToFractionPart(StringBuilder builder, int precision) {
        for (int i = builder.length(); i < precision; i++) {
            builder.append('0');
        }
        return builder;
    }

    private static StringBuilder convertBigInteger(BigInteger value, int base){
        StringBuilder intResult = new StringBuilder();
        boolean negative = (value.compareTo(BigInteger.ZERO) < 0);
        while (!value.equals(BigInteger.ZERO)) {
            intResult.append(DIGITS.get(value.remainder(BigInteger.valueOf(base)).intValue()));
            value = value.divide(BigInteger.valueOf(base));
        }
        if (negative) {
            intResult.append('-');
        }
        intResult.reverse();
        return intResult;
    }
}
