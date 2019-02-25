package stp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import stp.model.history.HistoryItem;

public class HistoryListViewController {

    @FXML
    private Label labelInputValue;

    @FXML
    private Label labelOutputValue;

    @FXML
    private Label labelInputBase;

    @FXML
    private Label labelOutputBase;

    @FXML
    private Label labelPrecision;

    @FXML
    private VBox mainPanel;

    public void setHistoryItemToView(HistoryItem historyItem) {
        labelInputValue.setText(historyItem.getFromValue());
        labelOutputValue.setText(historyItem.getToValue());
        labelInputBase.setText(String.valueOf(historyItem.getFromBase()));
        labelOutputBase.setText(String.valueOf(historyItem.getToBase()));
    }

    public VBox getMainPanel() {
        return mainPanel;
    }
}
