package com.example.projecctforandroidlessons.domain.usecase.courseusecase;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;


public class AddCourseUseCase {

    private final CourseRepository courseRepository;

    public AddCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public boolean execute(CourseDomain courseDomain) {
        new Thread(() -> {
            courseRepository.insert(courseDomain);
        }).start();
        return true;
    }
}
