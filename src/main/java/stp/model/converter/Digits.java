package stp.model.converter;

import java.util.HashMap;
import java.util.Map;

class Digits {
    private static final Map<Character, Integer> DIGITSFROMCHAR;
    private static final Map<Integer, Character> DIGITSFROMINT;

    static {
        DIGITSFROMCHAR = new HashMap<>();
        DIGITSFROMCHAR.put('0', 0);
        DIGITSFROMCHAR.put('1', 1);
        DIGITSFROMCHAR.put('2', 2);
        DIGITSFROMCHAR.put('3', 3);
        DIGITSFROMCHAR.put('4', 4);
        DIGITSFROMCHAR.put('5', 5);
        DIGITSFROMCHAR.put('6', 6);
        DIGITSFROMCHAR.put('7', 7);
        DIGITSFROMCHAR.put('8', 8);
        DIGITSFROMCHAR.put('9', 9);
        DIGITSFROMCHAR.put('A', 10);
        DIGITSFROMCHAR.put('B', 11);
        DIGITSFROMCHAR.put('C', 12);
        DIGITSFROMCHAR.put('D', 13);
        DIGITSFROMCHAR.put('E', 14);
        DIGITSFROMCHAR.put('F', 15);

        DIGITSFROMINT = new HashMap<>();
        DIGITSFROMINT.put(0, '0');
        DIGITSFROMINT.put(1, '1');
        DIGITSFROMINT.put(2, '2');
        DIGITSFROMINT.put(3, '3');
        DIGITSFROMINT.put(4, '4');
        DIGITSFROMINT.put(5, '5');
        DIGITSFROMINT.put(6, '6');
        DIGITSFROMINT.put(7, '7');
        DIGITSFROMINT.put(8, '8');
        DIGITSFROMINT.put(9, '9');
        DIGITSFROMINT.put(10, 'A');
        DIGITSFROMINT.put(11, 'B');
        DIGITSFROMINT.put(12, 'C');
        DIGITSFROMINT.put(13, 'D');
        DIGITSFROMINT.put(14, 'E');
        DIGITSFROMINT.put(15, 'F');
    }

    static int getDigitFromChar(char hexDigit) throws IllegalArgumentException {
        if (!DIGITSFROMCHAR.containsKey(hexDigit)) {
            throw new IllegalArgumentException("Digit must be from 0 to F");
        }
        return DIGITSFROMCHAR.get(hexDigit);
    }

    static char getDigitFromInt(int digit) throws IllegalArgumentException {
        if (!DIGITSFROMINT.containsKey(digit)) {
            throw new IllegalArgumentException("Digit must be from 0 to 15");
        }
        return DIGITSFROMINT.get(digit);
    }
}
