package com.example.projecctforandroidlessons.data.roomdb;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.projecctforandroidlessons.domain.models.StudentDomain;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String birthDate;

    // Getters and setters
    public int  getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    // Конвертеры
    public static Student fromDomain(StudentDomain domain) {
        Student student = new Student();
        student.setName(domain.getName());
        student.setEmail(domain.getEmail());
        student.setBirthDate(domain.getBirthDate());
        return student;
    }

    public StudentDomain toDomain() {
        return new StudentDomain(name, email, birthDate);
    }

}