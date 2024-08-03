package com.example.projecctforandroidlessons.domain.models;

import java.util.Objects;

public class CourseDomain {

    private String name;
    private int courseCode;
    private int credits;

    public CourseDomain(String name, int courseCode, int credits) {
        this.name = name;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public void setCredits(int credits) {
        this.credits = credits;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDomain that = (CourseDomain) o;
        return courseCode == that.courseCode && credits == that.credits && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseCode, credits);
    }

    @Override
    public String toString() {
        return "CourseDomain{" +
                "name='" + name + '\'' +
                ", courseCode=" + courseCode +
                ", credits=" + credits +
                '}';
    }
}
