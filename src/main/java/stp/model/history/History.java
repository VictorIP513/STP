package stp.model.history;

import java.util.Deque;
import java.util.LinkedList;

public class History {

    private static final int MAX_HISTORY_SIZE = 20;
    private static History history;
    private Deque<HistoryItem> historyList;

    private History() {
        historyList = new LinkedList<>();
    }

    public static History getInstance() {
        if (history == null) {
            history = new History();
        }
        return history;
    }

    public Deque<HistoryItem> getHistoryList() {
        return historyList;
    }

    public int getHistorySize() {
        return historyList.size();
    }

    public void addItem(HistoryItem item) {
        if (historyList.size() >= MAX_HISTORY_SIZE) {
            historyList.removeLast();
        }
        historyList.addFirst(item);
    }
}
