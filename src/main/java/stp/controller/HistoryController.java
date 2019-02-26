package stp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import stp.model.HistoryListViewCell;
import stp.model.HistoryModel;
import stp.model.history.HistoryItem;


public class HistoryController {

    @FXML
    private ListView<HistoryItem> listView;

    private HistoryModel historyModel;

    public void updateHistory() {
        ObservableList<HistoryItem> historyList = FXCollections.observableArrayList(historyModel.getHistory());
        listView.setItems(historyList);
    }

    @FXML
    public void initialize() {
        historyModel = new HistoryModel();
        listView.setCellFactory(cell -> new HistoryListViewCell());
    }
}
