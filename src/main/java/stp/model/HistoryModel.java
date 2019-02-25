package stp.model;

import stp.model.history.History;
import stp.model.history.HistoryItem;

import java.util.Collections;
import java.util.List;

public class HistoryModel {

    private static final History HISTORY;

    static {
        HISTORY = History.getInstance();
    }

    public List<HistoryItem> getHistory() {
        List<HistoryItem> history = HISTORY.getHistoryList();
        Collections.reverse(history);
        return history;
    }
}
