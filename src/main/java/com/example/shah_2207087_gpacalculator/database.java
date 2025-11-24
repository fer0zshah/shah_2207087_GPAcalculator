package com.example.shah_2207087_gpacalculator;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class database{

    private static final String DB_URL = "jdbc:sqlite:gpa_data.db";

    public static void initializeDB() {
        String sql = "CREATE TABLE IF NOT EXISTS courses (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "code TEXT," +
                "credit REAL," +
                "teacher1 TEXT," +
                "teacher2 TEXT," +
                "grade TEXT" +
                ")";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCourse(Course course) {
        String sql = "INSERT INTO courses(name, code, credit, teacher1, teacher2, grade) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseCode());
            pstmt.setDouble(3, course.getCredit());
            pstmt.setString(4, course.getTeacher1());
            pstmt.setString(5, course.getTeacher2());
            pstmt.setString(6, course.getGrade());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Course> getAllCourses() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getDouble("credit"),
                        rs.getString("teacher1"),
                        rs.getString("teacher2"),
                        rs.getString("grade")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}