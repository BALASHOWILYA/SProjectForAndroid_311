package com.example.projecctforandroidlessons.domain.usecase.courseusecase;

import com.example.projecctforandroidlessons.domain.repository.CourseRepository;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

public class DeleteCourseUseCase {


    private final CourseRepository courseRepository;

    public DeleteCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void execute(String name) {
        courseRepository.delete(name);
    }

}

