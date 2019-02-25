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

    private ObservableList<HistoryItem> historyList;

    private HistoryModel historyModel;

    @FXML
    public void initialize() {
        historyModel = new HistoryModel();
        historyList = FXCollections.observableArrayList();

        setupListView();
    }

    private void setupListView() {
        listView.setCellFactory(cell -> new HistoryListViewCell());
        listView.setItems(historyList);
    }
}
