package com.example.projecctforandroidlessons.domain.models;

import java.util.Date;

public class EnrollmentDomain {
    private int studentId;
    private int courseId;
    private Date enrollmentDate; // Дата должна быть типа Date

    // Конструктор
    public EnrollmentDomain(int studentId, int courseId, Date enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // Геттеры и сеттеры
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // Преобразование даты в строку для отображения
    public String getFormattedEnrollmentDate() {
        // Здесь можно использовать SimpleDateFormat для форматирования даты
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(enrollmentDate);
    }
}
