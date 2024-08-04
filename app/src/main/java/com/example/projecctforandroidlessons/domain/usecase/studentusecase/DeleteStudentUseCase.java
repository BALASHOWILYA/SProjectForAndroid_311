package com.example.projecctforandroidlessons.domain.usecase.studentusecase;

import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

public class DeleteStudentUseCase {

    private final StudentRepository studentRepository;

    public DeleteStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(String email) {
        studentRepository.deleteStudentByEmail(email);
    }

}


