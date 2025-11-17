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
//import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//requires javafx.controls;



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
        String courseName = NameFeild.getText();
        String courseCode = CodeFeild.getText();
        String creditStr = CreditFeild.getText();
        String teacher1 = Teacher1Feild.getText();
        String teacher2 = teacher2Feild.getText();
        String grade = gradebox.getValue();
        double credit= Double.parseDouble(creditStr);

        if (courseName.isEmpty() || courseCode.isEmpty() || creditStr.isEmpty()) {
            showError("Input Error", "Course Name, Code, and Credit cannot be empty.");
            return;
        }
        try {
            credit = Double.parseDouble(creditStr);
            if (credit <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Input Error", "Credit must be a positive number");
            return;
        }

        Course newCourse = new Course(courseName, courseCode, credit, teacher1, teacher2, grade);

        courseList.add(newCourse);

        currentTotalCredits += credit;
        currentCreditLabel.setText(String.format("%.1f", currentTotalCredits));
        clearFeilds(null);
    }

    @FXML
    void backToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void calculateButton(ActionEvent event) {
        String totalSemCreditsStr = totalCreditField.getText();
        double totalSemCredits;

        if (totalSemCreditsStr.isEmpty()) {
            showError("Input Error", "Total Semester Credits cannot be empty.");
            return;
        }

        try {
            totalSemCredits = Double.parseDouble(totalSemCreditsStr);
            if (totalSemCredits <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Input Error", "Total Semester Credits must be a positive number.");
            return;
        }

        if (courseList.isEmpty()) {
            showError("Calculation Error", "No courses added to calculate GPA.");
            return;
        }

        double total = 0.0;
        for (Course course : courseList) {
            total += getGradePoint(course.getGrade()) * course.getCredit();
        }

        double gpa = total / totalSemCredits;

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
            Parent root = loader.load();


            scene3controller resultController = loader.getController();

            resultController.setData(gpa);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GPA Result");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // If loading fails, show an error
            showError("Load Error", "Failed to load the result screen.");
        }


//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("GPA Result");
//        alert.setHeaderText("Your GPA has been calculated!");
//        alert.setContentText(String.format(
//                "Total Quality Points: %.2f\n" +
//                        "Total Semester Credits: %.1f\n\n" +
//                        "Your Semester GPA is: %.2f",
//                total, totalSemCredits, gpa
//        ));
//        alert.showAndWait();
    }

    private double getGradePoint(String grade) {
        if(grade=="A+")return 4.0;
        else if(grade=="A")return 3.75;
        else if(grade=="A-")return 3.50;
        else if(grade=="B+")return 3.25;
        else if(grade=="B")return 3.00;
        else if(grade=="B-")return 2.75;
        else if(grade=="C+")return 2.50;
        else if(grade=="C")return 2.25;
        else if(grade=="C-")return 2.00;
        else if(grade=="D")return 1.75;
        else return 0.0;
    }

    @FXML
    void clearFeilds(ActionEvent event) {
        NameFeild.clear();
        CodeFeild.clear();
        CreditFeild.clear();
        Teacher1Feild.clear();
        teacher2Feild.clear();
        gradebox.setValue("A+");
    }
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
