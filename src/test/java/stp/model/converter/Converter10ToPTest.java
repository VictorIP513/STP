package stp.model.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Converter10ToPTest {

    @ParameterizedTest
    @CsvSource({"161, 15, 5, AB", "5, 15, 5, 5", "256, 2, 10, 100000000"})
    void testConvertIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, Converter10ToP.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-161, 15, 5, -AB", "-5, 15, 5, -5", "-256, 2, 10, -100000000"})
    void testConvertNegativeIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, Converter10ToP.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"161.5, 15, 3, AB.777", "5.05, 15, 6, 5.0B3B3B", "256.001, 2, 0, 100000000"})
    void testConvertDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, Converter10ToP.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-161.5, 15, 3, -AB.777", "-0.05, 15, 6, -0.0B3B3B",
            "-256.001, 2, 0, -100000000", "-131.0, 15, 0, -8B"})
    void testConvertNegativeDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, Converter10ToP.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-5.2, 8, 4, -5.1463", "-22.15, 14, 4, -18.2158", "-333.333, 16, 4, -14D.553F"})
    void testNegativeValues(String value, int outputBase, int precision, String actualResult) {
        assertEquals(Converter10ToP.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"-5", "1", "20"})
    void testIncorrectBase(int base) {
        assertThrows(IllegalArgumentException.class, () -> Converter10ToP.convert("10", base, 10));
    }

    @ParameterizedTest
    @CsvSource({"-1", "101"})
    void testIncorrectPrecision(int precision) {
        assertThrows(IllegalArgumentException.class, () -> Converter10ToP.convert("10", 10, precision));
    }
}