package stp.model.converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Converter10ToP {

    private static final int MAXBASE = 16;
    private static final int MINBASE = 2;
    private static final int MAXPRECISION = 100;

    public static String convert(String valueString, int base, int precision) throws IllegalArgumentException {
        if (valueString.isEmpty()) {
            throw new IllegalArgumentException("Value string can not be empty");
        }
        if (base < MINBASE || base > MAXBASE) {
            throw new IllegalArgumentException("Base must be from " + MINBASE + " to " + MAXBASE);
        }
        if (precision < 0 || precision > MAXPRECISION) {
            throw new IllegalArgumentException("Precision must be from 0 to " + MAXPRECISION);
        }
        valueString = valueString.toUpperCase();
        BigDecimal value = new BigDecimal(valueString);
        value = value.stripTrailingZeros();
        BigInteger number = value.toBigInteger();
        StringBuilder intResult = convertBigInteger(number, base);

        if (precision == 0) {
            return intResult.toString();
        }

        if (value.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            StringBuilder sb = new StringBuilder();
            addZerosToFractionPart(sb, precision);
            return intResult.toString() + "." + sb.toString();
        }

        BigDecimal fraction = value.subtract(new BigDecimal(number));
        StringBuilder fractionResult = convertFraction(fraction, base, precision);
        intResult.append(".");
        if (fractionResult.length() > precision) {
            fractionResult.delete(precision, fractionResult.length());
        } else {
            addZerosToFractionPart(fractionResult, precision);
        }
        intResult.append(fractionResult);
        if (number.equals(BigInteger.ZERO) && value.compareTo(BigDecimal.ZERO) < 0){
            intResult.insert(0, '-');
        }
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

    private static void addZerosToFractionPart(StringBuilder builder, int precision) {
        for (int i = builder.length(); i < precision; i++) {
            builder.append('0');
        }
    }


    private static StringBuilder convertBigInteger(BigInteger value, int base) {
        if (value.equals(BigInteger.ZERO)) {
            return new StringBuilder("0");
        }
        boolean negative = value.compareTo(BigInteger.ZERO) < 0;
        value = value.abs();
        StringBuilder result = new StringBuilder();
        while (!value.equals(BigInteger.ZERO)) {
            result.append(Digits.getDigitFromInt(value.remainder(BigInteger.valueOf(base)).intValue()));
            value = value.divide(BigInteger.valueOf(base));
        }
        if (negative) {
            result.append('-');
        }
        result.reverse();
        return result;
    }

    private static StringBuilder convertFraction(BigDecimal value, int base, int precision) {
        BigDecimal multiplier = (BigDecimal.ONE.divide(BigDecimal.valueOf(base),
                (precision + 1) * 2, BigDecimal.ROUND_FLOOR));
        value = value.abs();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= precision; i++) {
            int digit = value.divideToIntegralValue(multiplier).intValue();
            if (digit > 0) {
                value = value.subtract(multiplier.multiply(BigDecimal.valueOf(digit)));
            }
            result.append(Digits.getDigitFromInt(digit));
            multiplier = multiplier.divide(BigDecimal.valueOf(base), (precision + 1) * 2, BigDecimal.ROUND_FLOOR);
        }
        return result;
    }
}
