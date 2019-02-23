package stp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stp.controller.ConverterController;
import stp.controller.HistoryController;
import stp.model.ConverterModel;
import stp.model.HistoryModel;

import java.io.IOException;

public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Stage converterWindow;
    private Stage historyWindow;

    private ConverterController converterController;
    private HistoryController historyController;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        converterWindow = primaryStage;

        try {
            setupConverterView();
            setupHistoryView();
        } catch (IOException e) {
            LOGGER.error("Error with opening resource file: {}", e.getMessage());
        }

        ConverterModel converterModel = new ConverterModel();
        HistoryModel historyModel = new HistoryModel();

        converterController.setConverterModel(converterModel);
        converterController.setHistoryWindow(historyWindow);

        historyController.setHistoryModel(historyModel);

        historyWindow.show();
    }

    private void setupConverterView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/converter.fxml"));
        Parent fxmlView = loader.load();
        converterWindow.setScene(new Scene(fxmlView));
        converterController = loader.getController();

        converterWindow.setTitle("Конвертер");
        converterWindow.setResizable(false);
    }

    private void setupHistoryView() throws IOException {
        historyWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/history.fxml"));
        Parent fxmlView = loader.load();
        historyWindow.setScene(new Scene(fxmlView));
        historyController = loader.getController();

        historyWindow.setTitle("История");
        historyWindow.setResizable(false);
        historyWindow.initModality(Modality.APPLICATION_MODAL);
    }
}
