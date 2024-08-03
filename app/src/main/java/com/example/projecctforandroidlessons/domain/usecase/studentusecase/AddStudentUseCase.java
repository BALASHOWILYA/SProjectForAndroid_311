package com.example.projecctforandroidlessons.domain.usecase.studentusecase;


import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

public class AddStudentUseCase {

    private final StudentRepository studentRepository;

    public AddStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean execute(StudentDomain studentDomain) {
        new Thread(() -> {
            studentRepository.insert(studentDomain);
        }).start();
        return true;
    }
}
