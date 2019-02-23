package stp.model;

import stp.model.converter.Converter10ToP;
import stp.model.converter.ConverterPTo10;

import java.util.HashMap;
import java.util.Map;

public class ConverterModel {

    private static final Map<Integer, String> VALIDATION_REGEXPS;

    static {
        VALIDATION_REGEXPS = new HashMap<>(15);
        VALIDATION_REGEXPS.put(2, "[0-1]+(\\.[0-1]+)?");
        VALIDATION_REGEXPS.put(3, "[0-2]+(\\.[0-2]+)?");
        VALIDATION_REGEXPS.put(4, "[0-3]+(\\.[0-3]+)?");
        VALIDATION_REGEXPS.put(5, "[0-4]+(\\.[0-4]+)?");
        VALIDATION_REGEXPS.put(6, "[0-5]+(\\.[0-5]+)?");
        VALIDATION_REGEXPS.put(7, "[0-6]+(\\.[0-6]+)?");
        VALIDATION_REGEXPS.put(8, "[0-7]+(\\.[0-7]+)?");
        VALIDATION_REGEXPS.put(9, "[0-8]+(\\.[0-8]+)?");
        VALIDATION_REGEXPS.put(10, "[0-9]+(\\.[0-9]+)?");
        VALIDATION_REGEXPS.put(11, "[0-9aA]+(\\.[0-9aA]+)?");
        VALIDATION_REGEXPS.put(12, "[0-9a-bA-B]+(\\.[0-9a-bA-B]+)?");
        VALIDATION_REGEXPS.put(13, "[0-9a-cA-C]+(\\.[0-9a-cA-C]+)?");
        VALIDATION_REGEXPS.put(14, "[0-9a-dA-D]+(\\.[0-9a-dA-D]+)?");
        VALIDATION_REGEXPS.put(15, "[0-9a-eA-E]+(\\.[0-9a-eA-E]+)?");
        VALIDATION_REGEXPS.put(16, "[0-9a-fA-F]+(\\.[0-9a-fA-F]+)?");
    }

    public boolean isCorrectInputValueFormValidation(String text, int base) {
        String validationRegExp = VALIDATION_REGEXPS.get(base);
        return text.matches(validationRegExp);
    }

    public String convertValue(String value, int inputBase, int outputBase, int precision) {
        String convertedTo10Base = ConverterPTo10.convert(value, inputBase, precision);
        return Converter10ToP.convert(convertedTo10Base, outputBase, precision);
    }

    public int getValueFromHexString(String str) {
        try {
            return Integer.parseInt(str, 16);
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }
}
