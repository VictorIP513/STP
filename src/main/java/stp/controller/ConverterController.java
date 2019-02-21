package stp.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import stp.model.ConverterModel;

public class ConverterController {

    @FXML
    private Spinner<Integer> spinnerInputBase;

    @FXML
    private Spinner<Integer> spinnerOutputBase;

    @FXML
    private Slider sliderInputBase;

    @FXML
    private Slider sliderOutputBase;

    @FXML
    private TextField textFieldInputValue;

    @FXML
    private TextField textFieldIOutputValue;

    private ConverterModel converterModel;

    public ConverterController() {
        converterModel = new ConverterModel();
    }

    @FXML
    public void initialize() {
        configureSpinners();
        configureSliders();
        configureTextFields();
    }

    @FXML
    private void buttonExecuteOnMouseClicked(MouseEvent mouseEvent) {
        String value = textFieldInputValue.getText();
        int inputBase = spinnerInputBase.getValue();
        int outputBase = spinnerOutputBase.getValue();
        int precision = 10;
        String result = converterModel.convertValue(value, inputBase, outputBase, precision);
        textFieldIOutputValue.setText(result);
    }

    private void configureSpinners() {
        spinnerInputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> sliderInputBase.setValue(newValue));
        spinnerOutputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> sliderOutputBase.setValue(newValue));

        disableContextMenu(spinnerInputBase);
        disableContextMenu(spinnerOutputBase);
    }

    private void configureSliders() {
        sliderInputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> spinnerInputBase.getValueFactory().setValue(newValue.intValue()));
        sliderOutputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> spinnerOutputBase.getValueFactory().setValue(newValue.intValue()));
    }

    private void configureTextFields() {
        configureInputTextField();
        configureOutputTextField();
    }

    private <T extends Node> void disableContextMenu(T element) {
        element.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    }

    private void configureInputTextField() {
        textFieldInputValue.textProperty().addListener((observable, oldValue, newValue) -> {
            int currentBase = spinnerInputBase.getValue();
            if (newValue.isEmpty()) {
                return;
            }
            if (!converterModel.isCorrectInputValueFormValidation(newValue, currentBase)) {
                if (!textFieldInputValue.getStyleClass().contains("form_validation_error")) {
                    textFieldInputValue.getStyleClass().add("form_validation_error");
                }
            } else {
                textFieldInputValue.getStyleClass().remove("form_validation_error");
            }
        });

        disableContextMenu(textFieldInputValue);
    }

    private void configureOutputTextField() {
        disableContextMenu(textFieldIOutputValue);
    }
}
