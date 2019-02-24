package stp.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import stp.model.ConverterModel;

public class ConverterController {

    @FXML
    private Spinner<Integer> spinnerInputBase;

    @FXML
    private Spinner<Integer> spinnerOutputBase;

    @FXML
    private Spinner<Integer> spinnerPrecision;

    @FXML
    private Slider sliderInputBase;

    @FXML
    private Slider sliderOutputBase;

    @FXML
    private TextField textFieldInputValue;

    @FXML
    private TextField textFieldIOutputValue;

    @FXML
    private Button buttonExecute;

    @FXML
    private GridPane gridPaneButtons;

    private ConverterModel converterModel;

    private Stage historyWindow;

    public void setHistoryWindow(Stage historyWindow) {
        this.historyWindow = historyWindow;
    }

    @FXML
    public void initialize() {
        converterModel = new ConverterModel();
        configureSpinners();
        configureSliders();
        configureTextFields();
        updateButtons();
    }

    @FXML
    private void buttonExecuteClicked() {
        String value = textFieldInputValue.getText();
        int inputBase = spinnerInputBase.getValue();
        int outputBase = spinnerOutputBase.getValue();
        int precision = spinnerPrecision.getValue();
        String result = converterModel.convertValue(value, inputBase, outputBase, precision);
        textFieldIOutputValue.setText(result);
    }

    @FXML
    private void buttonDigitClicked(MouseEvent event) {
        Button button = (Button) event.getSource();
        String digitText = button.getText();
        textFieldInputValue.setText(textFieldInputValue.getText() + digitText);
    }

    @FXML
    private void buttonClearClicked() {
        textFieldInputValue.clear();
        textFieldIOutputValue.clear();
    }

    @FXML
    private void buttonBackSpaceClicked() {
        String textInInputTextField = textFieldInputValue.getText();
        if (!textInInputTextField.isEmpty()) {
            textFieldInputValue.setText(textInInputTextField.substring(0, textInInputTextField.length() - 1));
        }
    }

    @FXML
    private void menuExitClick() {
        Platform.exit();
    }

    @FXML
    private void menuHistoryClick() {
        historyWindow.show();
    }

    @FXML
    private void menuHelpClick() {
    }

    private void configureSpinners() {
        spinnerInputBase.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderInputBase.setValue(newValue);
            updateButtons();
            updateInputTextField();
        });
        spinnerOutputBase.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderOutputBase.setValue(newValue);
            updateButtons();
        });

        disableContextMenu(spinnerInputBase);
        disableContextMenu(spinnerOutputBase);
    }

    private void configureSliders() {
        sliderInputBase.valueProperty().addListener((observable, oldValue, newValue) -> {
            spinnerInputBase.getValueFactory().setValue(newValue.intValue());
            updateButtons();
            updateInputTextField();
        });
        sliderOutputBase.valueProperty().addListener((observable, oldValue, newValue) -> {
            spinnerOutputBase.getValueFactory().setValue(newValue.intValue());
            updateButtons();
        });
    }

    private void configureTextFields() {
        configureInputTextField();
        configureOutputTextField();
    }

    private void configureInputTextField() {
        textFieldInputValue.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldInputValue.setText(newValue.toUpperCase());
            String textFieldValidationErrorStyleName = "form_validation_error";
            int currentBase = spinnerInputBase.getValue();

            if (newValue.isEmpty()) {
                textFieldInputValue.getStyleClass().remove(textFieldValidationErrorStyleName);
                buttonExecute.setDisable(true);
                return;
            }
            if (!converterModel.isCorrectInputValueFormValidation(newValue, currentBase)) {
                if (!textFieldInputValue.getStyleClass().contains(textFieldValidationErrorStyleName)) {
                    textFieldInputValue.getStyleClass().add(textFieldValidationErrorStyleName);
                }
                buttonExecute.setDisable(true);
            } else {
                textFieldInputValue.getStyleClass().remove(textFieldValidationErrorStyleName);
                buttonExecute.setDisable(false);
            }
        });

        disableContextMenu(textFieldInputValue);
    }

    private void configureOutputTextField() {
        disableContextMenu(textFieldIOutputValue);
    }

    private void updateButtons() {
        int base = spinnerInputBase.getValue();
        ObservableList<Node> buttons = gridPaneButtons.getChildren();
        for (Node element : buttons) {
            Button button = (Button) element;
            int buttonDigitText = converterModel.getValueFromHexString(button.getText());
            if (buttonDigitText != -1) {
                button.setDisable(buttonDigitText >= base);
            }
        }
    }

    private void updateInputTextField() {
        String inputText = textFieldInputValue.getText();
        textFieldInputValue.setText(textFieldInputValue.getText() + " ");
        textFieldInputValue.setText(inputText);
    }

    private <T extends Node> void disableContextMenu(T element) {
        element.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    }
}
