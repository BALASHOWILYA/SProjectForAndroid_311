package com.example.projecctforandroidlessons.domain.usecase.studentusecase;

import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

public class UpdateStudentUseCase {

    private final StudentRepository studentRepository;

    public UpdateStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(StudentDomain studentDomain) {
        new Thread(() -> {
            studentRepository.update(studentDomain);
        }).start();
    }
}
