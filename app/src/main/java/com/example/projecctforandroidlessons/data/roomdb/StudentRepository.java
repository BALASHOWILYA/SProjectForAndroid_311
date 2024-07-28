package com.example.projecctforandroidlessons.data.roomdb;


import com.example.projecctforandroidlessons.domain.StudentDomain;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {
    private final StudentDao studentDao;

    public StudentRepository(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void insert(StudentDomain studentDomain) {
        // Вызываем метод из слоя данных
        new Thread(() -> studentDao.insert(Student.fromDomain(studentDomain))).start();
    }

    public List<StudentDomain> getAllStudents() {
        // Вызываем метод из слоя данных и конвертируем его в доменные модели
        List<Student> students = studentDao.getAllStudents();

        return students.stream()
                .map(Student::toDomain)
                .collect(Collectors.toList());
    }
}