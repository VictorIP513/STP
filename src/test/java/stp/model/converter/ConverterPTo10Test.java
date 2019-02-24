package stp.model.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ConverterPTo10Test {

    @ParameterizedTest
    @CsvSource({"22, 4, 5, 10", "100, 8, 5, 64", "256, 16, 10, 598"})
    void testConvertIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, ConverterPTo10.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-22, 4, 5, -10", "-100, 8, 5, -64", "-256, 16, 10, -598"})
    void testConvertNegativeIntegerValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, ConverterPTo10.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"AB.77, 12, 3, 131.631", "5.05, 15, 6, 5.022222", "11.ABC, 16, 6, 17.670898"})
    void testConvertDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, ConverterPTo10.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-AB.77, 12, 3, -131.631", "-0.05, 15, 6, -0.022222", "-11.ABC, 16, 6, -17.670898",
    "-0.cbd, 16, 6, -0.796142", "-0.0, 16, 6, 0.000000"})
    void testConvertNegativeDoubleValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, ConverterPTo10.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"ab.77, 12, 3, 131.631", "b.12, 15, 6, 11.075555", "11.abc, 16, 6, 17.670898"})
    void testConvertLowerCaseValue(String value, int outputBase, int precision, String actualResult) {
        assertEquals(actualResult, ConverterPTo10.convert(value, outputBase, precision));
    }

    @ParameterizedTest
    @CsvSource({"-110.11, 2, 2, -6.75", "-DEAD.DEAD, 16, 4, -57005.8698", "-105.576, 8, 8, -69.74609375"})
    void testNegativeValues(String value, int outputBase, int precision, String actualResult) {
        assertEquals(ConverterPTo10.convert(value, outputBase, precision), actualResult);
    }

    @ParameterizedTest
    @CsvSource({"-5", "1", "20"})
    void testIncorrectBase(int base) {
        assertThrows(IllegalArgumentException.class, () -> ConverterPTo10.convert("10", base, 10));
    }

    @ParameterizedTest
    @CsvSource({"-1", "101"})
    void testIncorrectPrecision(int precision) {
        assertThrows(IllegalArgumentException.class, () -> ConverterPTo10.convert("10", 10, precision));
    }
}