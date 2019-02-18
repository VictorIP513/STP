package stp.model;

public class HistoryItem {
    private String fromValue;
    private String toValue;
    private int fromBase;
    private int toBase;

    public HistoryItem(String fromValue, String toValue, int fromBase, int toBase) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.fromBase = fromBase;
        this.toBase = toBase;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public int getFromBase() {
        return fromBase;
    }

    public int getToBase() {
        return toBase;
    }
}
