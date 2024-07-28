package com.example.projecctforandroidlessons.data.roomdb;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int courseCode;
    private int credits;



    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }


    public int getCourseCode() { return courseCode; }
    public void setCourseCode(int courseCode) { this.courseCode = courseCode; }
}