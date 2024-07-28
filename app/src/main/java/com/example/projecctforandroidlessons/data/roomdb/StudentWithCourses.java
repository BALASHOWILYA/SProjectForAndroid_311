package com.example.projecctforandroidlessons.data.roomdb;



import java.util.Objects;

public class StudentWithCourses {
    private final String studentName;
    private final String courseName;
    private final String enrollmentDate;

    // Getters and setters
    public String getStudentName() { return studentName; }


    public String getCourseName() { return courseName; }


    public String getEnrollmentDate() { return enrollmentDate; }


    public StudentWithCourses(String studentName, String courseName, String enrollmentDate) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.enrollmentDate = enrollmentDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentWithCourses that = (StudentWithCourses) o;
        return Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName) && Objects.equals(enrollmentDate, that.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName, enrollmentDate);
    }
}