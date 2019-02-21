package stp.model;

import stp.model.converter.Converter10ToP;
import stp.model.converter.ConverterPTo10;

import java.util.HashMap;
import java.util.Map;

public class ConverterModel {

    private static final Map<Integer, String> VALIDATION_REGEXPS;

    static {
        VALIDATION_REGEXPS = new HashMap<>(14);
        VALIDATION_REGEXPS.put(2, "");
        VALIDATION_REGEXPS.put(3, "");
        VALIDATION_REGEXPS.put(4, "");
        VALIDATION_REGEXPS.put(5, "");
        VALIDATION_REGEXPS.put(6, "");
        VALIDATION_REGEXPS.put(7, "");
        VALIDATION_REGEXPS.put(8, "");
        VALIDATION_REGEXPS.put(9, "");
        VALIDATION_REGEXPS.put(10, "");
        VALIDATION_REGEXPS.put(11, "");
        VALIDATION_REGEXPS.put(12, "");
        VALIDATION_REGEXPS.put(13, "");
        VALIDATION_REGEXPS.put(14, "");
        VALIDATION_REGEXPS.put(15, "");
        VALIDATION_REGEXPS.put(16, "");
    }

    public boolean isCorrectInputValueFormValidation(String text, int base) {
        return false;
    }

    public String convertValue(String value, int inputBase, int outputBase, int precision) {
        String convertedTo10Base = ConverterPTo10.convert(value, inputBase, precision);
        return Converter10ToP.convert(convertedTo10Base, outputBase, precision);
    }
}
