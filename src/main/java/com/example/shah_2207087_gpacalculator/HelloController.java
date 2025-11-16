package com.example.shah_2207087_gpacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // Import FXMLLoader
import javafx.scene.Node;       // Import Node
import javafx.scene.Parent;     // Import Parent
import javafx.scene.Scene;       // Import Scene
import javafx.scene.control.Button;
import javafx.stage.Stage;       // Import Stage

import java.io.IOException; // Import IOException

public class HelloController {

    @FXML
    private Button start;

    @FXML
    void clickStart(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}