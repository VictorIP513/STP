package stp.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stp.controller.HistoryListViewController;
import stp.model.history.HistoryItem;

import java.io.IOException;

public class HistoryListViewCell extends ListCell<HistoryItem> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryListViewCell.class);

    private HistoryListViewController historyListViewController;

    public HistoryListViewCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/history_cell.fxml"));
        try {
            loader.load();
            historyListViewController = loader.getController();
        } catch (IOException e) {
            LOGGER.error("Error with opening resource file: {}", e.getMessage());
        }
        historyListViewController = loader.getController();
    }

    @Override
    protected void updateItem(HistoryItem item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            historyListViewController.setHistoryItemToView(item);
            setGraphic(historyListViewController.getMainPanel());
        }
    }
}
