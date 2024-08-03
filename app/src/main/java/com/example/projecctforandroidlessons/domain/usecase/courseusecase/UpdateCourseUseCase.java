package com.example.projecctforandroidlessons.domain.usecase.courseusecase;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;
import com.example.projecctforandroidlessons.domain.repository.StudentRepository;

public class UpdateCourseUseCase {

    private final CourseRepository courseRepository;

    public UpdateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void execute(CourseDomain courseDomain) {
        new Thread(() -> {
            courseRepository.update(courseDomain);
        }).start();
    }
}

