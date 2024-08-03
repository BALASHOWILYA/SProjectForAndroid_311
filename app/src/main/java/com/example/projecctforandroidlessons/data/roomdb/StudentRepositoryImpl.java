package com.example.projecctforandroidlessons.data.roomdb;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;


public class StudentRepositoryImpl implements StudentRepository {
    private final StudentDao studentDao;

    public StudentRepositoryImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void insert(StudentDomain studentDomain) {
        // Вызываем метод из слоя данных
        new Thread(() -> studentDao.insert(Student.fromDomain(studentDomain))).start();
    }

    @Override
    public void update(StudentDomain studentDomain) {
        new Thread(() -> studentDao.update(Student.fromDomain(studentDomain))).start();
    }

    @Override
    public void deleteStudentByEmail(String email) {
        new Thread(() -> {
            Student student = studentDao.findStudentByEmail(email);
            if (student != null) {
                studentDao.delete(student);
            }
        }).start();
    }

    @Override
    public StudentDomain findStudentByEmail(String email) {
        Callable<Student> callable = () -> studentDao.findStudentByEmail(email);
        FutureTask<Student> futureTask = new FutureTask<>(callable);
        Executors.newSingleThreadExecutor().submit(futureTask);
        try {
            Student student = futureTask.get();
            if (student != null) {
                return student.toDomain();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentDomain> getAllStudents() {
        // Вызываем метод из слоя данных и конвертируем его в доменные модели
        List<Student> students = studentDao.getAllStudents();

        return students.stream()
                .map(Student::toDomain)
                .collect(Collectors.toList());
    }
}