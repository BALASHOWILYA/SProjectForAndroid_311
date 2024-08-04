package com.example.projecctforandroidlessons.domain.repository;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import java.util.List;

public interface StudentRepository {
    void insert(StudentDomain studentDomain);
    void update(StudentDomain studentDomain);
    void deleteStudentByEmail(String email);
    StudentDomain findStudentByEmail(String email);
    List<StudentDomain> getAllStudents();
}
