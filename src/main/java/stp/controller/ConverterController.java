package stp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;

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
    public void initialize() {
        configureSpinners();
        configureSliders();
    }

    private void configureSpinners() {
        spinnerInputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> sliderInputBase.setValue(newValue));
        spinnerOutputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> sliderOutputBase.setValue(newValue));
    }

    private void configureSliders() {
        sliderInputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> spinnerInputBase.getValueFactory().setValue(newValue.intValue()));
        sliderOutputBase.valueProperty().addListener(
                (observable, oldValue, newValue) -> spinnerOutputBase.getValueFactory().setValue(newValue.intValue()));
    }
}
