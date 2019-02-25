package stp.model.history;

public class HistoryItem {

    private String inputValue;
    private String outputValue;
    private int inputBase;
    private int outputBase;
    private int precision;

    public HistoryItem(String inputValue, String outputValue, int inputBase, int outputBase, int precision) {
        this.inputValue = inputValue;
        this.outputValue = outputValue;
        this.inputBase = inputBase;
        this.outputBase = outputBase;
        this.precision = precision;
    }

    public String getInputValue() {
        return inputValue;
    }

    public String getOutputValue() {
        return outputValue;
    }

    public int getInputBase() {
        return inputBase;
    }

    public int getOutputBase() {
        return outputBase;
    }

    public int getPrecision() {
        return precision;
    }
}
