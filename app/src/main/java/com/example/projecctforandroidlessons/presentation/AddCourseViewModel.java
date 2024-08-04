package com.example.projecctforandroidlessons.presentation;

import androidx.lifecycle.ViewModel;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.usecase.courseusecase.AddCourseUseCase;


public class AddCourseViewModel extends ViewModel {

    private final AddCourseUseCase addCourseUseCase;

    public AddCourseViewModel(AddCourseUseCase addCourseUseCase) {
        this.addCourseUseCase = addCourseUseCase;
    }

    public void addCourse(CourseDomain courseDomain) {
        new Thread(() -> {
            addCourseUseCase.execute(courseDomain);
        }).start();
    }


}