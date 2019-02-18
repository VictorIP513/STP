package stp.model.converter;

import java.math.BigInteger;

public abstract class ConverterPTo10 {
    private static final int MAXBASE = 16;
    private static final int MINBASE = 2;
    private static final int MAXPRECISION = 100;

    public static String convert(String value, int base, int precision) throws IllegalArgumentException {
        if (base < MINBASE || base > MAXBASE) {
            throw new IllegalArgumentException("Base must be from " + MINBASE + " to " + MAXBASE);
        }
        if (precision < 0 || precision > MAXPRECISION) {
            throw new IllegalArgumentException("Precision must be from 0 to " + MAXPRECISION);
        }
        value = cutTrailingZeros(value);
        String[] stringArray = value.split("\\.");
        BigInteger number = convertBigInteger(stringArray[0], base);
        String fraction = "";
        if (stringArray.length > 1) {
            fraction = convertBigInteger(stringArray[1], base).toString();
            fraction = zerosAfterComma(value) + fraction;
        }
        if (precision > 0) {
            StringBuilder sb = addZerosToFractionPart(new StringBuilder(fraction), precision);
            return number.toString() + "." + sb.substring(0, precision);
        }
        return number.toString();
    }

    private static BigInteger convertBigInteger(String value, int base) {
        boolean negative = value.startsWith("-");
        if (negative) value = value.substring(1, value.length() - 1);
        BigInteger multiplier = new BigInteger("1");
        BigInteger result = new BigInteger("0");
        for (int i = value.length() - 1; i >= 0; i--) {
            result = result.add(multiplier.multiply(BigInteger.valueOf(Digits.getDigitFromChar(value.charAt(i)))));
            multiplier = multiplier.multiply(BigInteger.valueOf(base));
        }
        if (negative) result = result.negate();
        return result;
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

    private static String cutTrailingZeros(String value) {
        StringBuilder sb = new StringBuilder(value);
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
