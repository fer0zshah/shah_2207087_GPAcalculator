package com.example.shah_2207087_gpacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class scene3controller {

    @FXML
    private Label gpaLabel;

    public void setData(double gpa) {
        gpaLabel.setText(String.format("Your GPA: %.2f", gpa));
    }

    @FXML
    void newCalculation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Hello.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}