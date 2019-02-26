package stp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stp.controller.ConverterController;
import stp.controller.HistoryController;

import java.io.IOException;

public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Stage converterWindow;
    private Stage historyWindow;

    private ConverterController converterController;

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

        converterController.setHistoryWindow(historyWindow);
        converterWindow.show();
    }

    private void setupConverterView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/converter.fxml"));
        Parent fxmlView = loader.load();
        converterWindow.setScene(new Scene(fxmlView));
        converterController = loader.getController();

        converterWindow.setTitle("Converter");
        converterWindow.setResizable(false);
    }

    private void setupHistoryView() throws IOException {
        historyWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/history.fxml"));
        Parent fxmlView = loader.load();
        historyWindow.setScene(new Scene(fxmlView));
        HistoryController historyController = loader.getController();

        historyWindow.setTitle("History");
        historyWindow.initModality(Modality.APPLICATION_MODAL);
        historyWindow.addEventHandler(WindowEvent.WINDOW_SHOWN, window -> historyController.updateHistory());
    }
}
