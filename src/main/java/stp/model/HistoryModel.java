package stp.model;

import stp.model.history.History;
import stp.model.history.HistoryItem;

import java.util.Deque;

public class HistoryModel {

    private static final History HISTORY;

    static {
        HISTORY = History.getInstance();
    }

    public Deque<HistoryItem> getHistory() {
        return HISTORY.getHistoryList();
    }
}
