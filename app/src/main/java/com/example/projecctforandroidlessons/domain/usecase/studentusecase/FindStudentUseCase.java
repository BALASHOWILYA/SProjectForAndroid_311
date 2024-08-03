package com.example.projecctforandroidlessons.domain.usecase.studentusecase;

import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FindStudentUseCase {

    private final StudentRepository studentRepository;

    public FindStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDomain execute(String email) {
        Callable<StudentDomain> callable = () -> studentRepository.findStudentByEmail(email);
        FutureTask<StudentDomain> futureTask = new FutureTask<>(callable);
        Executors.newSingleThreadExecutor().submit(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
