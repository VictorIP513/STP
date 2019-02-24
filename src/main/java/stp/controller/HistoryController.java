package stp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import stp.model.HistoryModel;
import stp.model.history.HistoryItem;

public class HistoryController {

    @FXML
    private ListView<HistoryItem> listView;

    private HistoryModel historyModel;

    @FXML
    public void initialize() {
        historyModel = new HistoryModel();
    }
}
