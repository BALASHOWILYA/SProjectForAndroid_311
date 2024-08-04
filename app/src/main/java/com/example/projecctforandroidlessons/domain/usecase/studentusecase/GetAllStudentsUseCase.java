package com.example.projecctforandroidlessons.domain.usecase.studentusecase;

import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class GetAllStudentsUseCase {

    private final StudentRepository studentRepository;



    public GetAllStudentsUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDomain> execute() {
        Callable<List<StudentDomain>> callable = () -> studentRepository.getAllStudents();
        FutureTask<List<StudentDomain>> futureTask = new FutureTask<>(callable);
        Executors.newSingleThreadExecutor().submit(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


}