package com.example.projecctforandroidlessons.domain.usecase.courseusecase;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetAllCoursesUseCase {

    private final CourseRepository courseRepository;

    public GetAllCoursesUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDomain> execute() {
        CompletableFuture<List<CourseDomain>> future = CompletableFuture.supplyAsync(courseRepository::getAllCourses);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
