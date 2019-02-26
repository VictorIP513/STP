package stp.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class HelpController {

    @FXML
    private VBox vBox;

    @FXML
    public void initialize() {
        Text text = new Text("The best converter for floating point numbers.\n");
        Text text1 = new Text("The workflow of this application is very simple.\n" +
                "We as the engineering team of this converter\n" +
                "hope that you will find it as easy as we see it.\n" +
                "Best regards, Komarevtsev Daniil and Minakov Viktor.");
        text.setFont(Font.font("Courier New", FontPosture.REGULAR, 16));
        text1.setFont(Font.font("Courier New", FontPosture.REGULAR, 14));
        vBox.getChildren().addAll(text, text1);
    }
}
