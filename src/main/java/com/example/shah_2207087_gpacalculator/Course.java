package com.example.shah_2207087_gpacalculator;

public class Course {
    private String courseName;
    private String courseCode;
    private double credit;
    private String teacher1;
    private String teacher2;
    private String grade;

    // Constructor
    public Course(String courseName, String courseCode, double credit, String teacher1, String teacher2, String grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getCredit() {
        return credit;
    }

    public String getTeacher1() {
        return teacher1;
    }

    public String getTeacher2() {
        return teacher2;
    }

    public String getGrade() {
        return grade;
    }
}
