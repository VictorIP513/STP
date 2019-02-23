package stp.model.history;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static final int MAX_HISTORY_SIZE = 20;
    private List<HistoryItem> historyList;
    private static History history;

    private History() {
        historyList = new ArrayList<>();
    }

    public History getHistory() {
        if (history == null) {
            history = new History();
        }
        return history;
    }

    public List<HistoryItem> getHistoryList() {
        return historyList;
    }

    public HistoryItem getHistoryItem(int index) {
        if (index >= historyList.size()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return historyList.get(index);
    }

    public int getHistorySize() {
        return historyList.size();
    }

    public void addItem(HistoryItem item) {
        historyList.add(item);
    }

}
