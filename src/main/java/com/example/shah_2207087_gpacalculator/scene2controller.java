package com.example.shah_2207087_gpacalculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class scene2controller implements Initializable {
    @FXML
    private TextField CodeFeild,CreditFeild,NameFeild,Teacher1Feild,teacher2Feild,totalCreditField;
    @FXML
    private Label currentCreditLabel;
    @FXML
    private ComboBox<String> gradebox;
    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView<Course> CourseTable;
    @FXML
    private TableColumn<Course, String> colCourseName,colCourseCode,colTeacher1, colTeacher2, colGrade;
    @FXML
    private TableColumn<Course, Double> colCredit;

    private ObservableList<Course> courseList = FXCollections.observableArrayList();
    private double currentTotalCredits = 0.0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> grades = FXCollections.observableArrayList(
                "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"
        );
        gradebox.setItems(grades);
        gradebox.setValue("A+");

        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colTeacher1.setCellValueFactory(new PropertyValueFactory<>("teacher1"));
        colTeacher2.setCellValueFactory(new PropertyValueFactory<>("teacher2"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        CourseTable.setItems(courseList);
    }




    @FXML
    void addCourse(ActionEvent event) {

    }

    @FXML
    void backToHome(ActionEvent event) {

    }

    @FXML
    void calculateButton(ActionEvent event) {

    }

    @FXML
    void clearFeilds(ActionEvent event) {

    }
}
