package com.example.projecctforandroidlessons.domain;


import java.util.Objects;

public class StudentDomain {

    private String name;
    private String email;
    private String birthDate;



    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public StudentDomain( String name, String email, String birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDomain that = (StudentDomain) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, birthDate);
    }

    @Override
    public String toString() {
        return "StudentDomain{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}