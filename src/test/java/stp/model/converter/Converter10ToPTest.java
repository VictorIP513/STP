package stp.model.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class Converter10ToPTest {

    @ParameterizedTest
    @CsvSource({"161, 15, 5, AB", "5, 15, 5, 5", "256, 2, 10, 100000000"})
    void testConvertIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(Converter10ToP.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"161.5, 15, 3, AB.777", "5.05, 15, 6, 5.0B3B3B", "256.001, 2, 0, 100000000"})
    void testConvertDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(Converter10ToP.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"-5", "1", "20"})
    void testIncorrectBase(int base) {
        assertThrows(IllegalArgumentException.class, () -> Converter10ToP.convert("10", base, 10));
    }

    @ParameterizedTest
    @CsvSource({"-1", "21"})
    void testIncorrectPrecision(int precision) {
        assertThrows(IllegalArgumentException.class, () -> Converter10ToP.convert("10", 10, precision));
    }
}