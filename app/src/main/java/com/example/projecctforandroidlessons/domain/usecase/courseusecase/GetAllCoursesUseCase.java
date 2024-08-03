package com.example.projecctforandroidlessons.domain.usecase.courseusecase;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class GetAllCoursesUseCase {

    private final CourseRepository courseRepository;

    public GetAllCoursesUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDomain> execute() {
        Callable<List<CourseDomain>> callable = () -> courseRepository.getAllCourses();
        FutureTask<List<CourseDomain>> futureTask = new FutureTask<>(callable);
        Executors.newSingleThreadExecutor().submit(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
