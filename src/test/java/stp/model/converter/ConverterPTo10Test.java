package stp.model.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ConverterPTo10Test {

    @ParameterizedTest
    @CsvSource({"22, 4, 5, 10", "100, 8, 5, 64", "256, 16, 10, 598"})
    void testConvertIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(ConverterPTo10.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"AB.77, 12, 3, 131.631", "5.05, 15, 6, 5.0B3B3B", "11.ABC, 16, 6, 17.670898"})
    void testConvertDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(ConverterPTo10.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"ab.77, 12, 3, 131.631", "b.12, 15, 6, 11.075555", "11.abc, 16, 6, 17.670898"})
    void testConvertLowerCaseValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(ConverterPTo10.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"-5", "1", "20"})
    void testIncorrectBase(int base) {
        assertThrows(IllegalArgumentException.class, () -> ConverterPTo10.convert("10", base, 10));
    }

    @ParameterizedTest
    @CsvSource({"-1", "21"})
    void testIncorrectPrecision(int precision) {
        assertThrows(IllegalArgumentException.class, () -> ConverterPTo10.convert("10", 10, precision));
    }
}