package com.example.projecctforandroidlessons.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projecctforandroidlessons.domain.usecase.courseusecase.AddCourseUseCase;

public class AddCourseViewModelFactory implements ViewModelProvider.Factory {

    private final AddCourseUseCase addCourseUseCase;

    public AddCourseViewModelFactory(AddCourseUseCase addCourseUseCase) {
        this.addCourseUseCase = addCourseUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddCourseViewModel.class)) {
            return (T) new AddCourseViewModel(addCourseUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
