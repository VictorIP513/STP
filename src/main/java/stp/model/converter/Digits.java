package stp.model.converter;

import java.util.HashMap;
import java.util.Map;

class Digits {

    private static final Map<Character, Integer> DIGITS_FROM_CHAR;
    private static final Map<Integer, Character> DIGITS_FROM_INT;

    static {
        DIGITS_FROM_CHAR = new HashMap<>();
        DIGITS_FROM_CHAR.put('0', 0);
        DIGITS_FROM_CHAR.put('1', 1);
        DIGITS_FROM_CHAR.put('2', 2);
        DIGITS_FROM_CHAR.put('3', 3);
        DIGITS_FROM_CHAR.put('4', 4);
        DIGITS_FROM_CHAR.put('5', 5);
        DIGITS_FROM_CHAR.put('6', 6);
        DIGITS_FROM_CHAR.put('7', 7);
        DIGITS_FROM_CHAR.put('8', 8);
        DIGITS_FROM_CHAR.put('9', 9);
        DIGITS_FROM_CHAR.put('A', 10);
        DIGITS_FROM_CHAR.put('B', 11);
        DIGITS_FROM_CHAR.put('C', 12);
        DIGITS_FROM_CHAR.put('D', 13);
        DIGITS_FROM_CHAR.put('E', 14);
        DIGITS_FROM_CHAR.put('F', 15);

        DIGITS_FROM_INT = new HashMap<>();
        DIGITS_FROM_INT.put(0, '0');
        DIGITS_FROM_INT.put(1, '1');
        DIGITS_FROM_INT.put(2, '2');
        DIGITS_FROM_INT.put(3, '3');
        DIGITS_FROM_INT.put(4, '4');
        DIGITS_FROM_INT.put(5, '5');
        DIGITS_FROM_INT.put(6, '6');
        DIGITS_FROM_INT.put(7, '7');
        DIGITS_FROM_INT.put(8, '8');
        DIGITS_FROM_INT.put(9, '9');
        DIGITS_FROM_INT.put(10, 'A');
        DIGITS_FROM_INT.put(11, 'B');
        DIGITS_FROM_INT.put(12, 'C');
        DIGITS_FROM_INT.put(13, 'D');
        DIGITS_FROM_INT.put(14, 'E');
        DIGITS_FROM_INT.put(15, 'F');
    }

    private Digits() {

    }

    static int getDigitFromChar(char hexDigit) {
        if (!DIGITS_FROM_CHAR.containsKey(hexDigit)) {
            throw new IllegalArgumentException("Digit must be from 0 to F");
        }
        return DIGITS_FROM_CHAR.get(hexDigit);
    }

    static char getDigitFromInt(int digit) {
        if (!DIGITS_FROM_INT.containsKey(digit)) {
            throw new IllegalArgumentException("Digit must be from 0 to 15");
        }
        return DIGITS_FROM_INT.get(digit);
    }
}
