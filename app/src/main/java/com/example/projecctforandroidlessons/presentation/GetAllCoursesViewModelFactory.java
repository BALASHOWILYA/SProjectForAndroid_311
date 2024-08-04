package com.example.projecctforandroidlessons.presentation;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projecctforandroidlessons.domain.usecase.courseusecase.GetAllCoursesUseCase;

public class GetAllCoursesViewModelFactory implements ViewModelProvider.Factory {

    private final GetAllCoursesUseCase getAllCoursesUseCase;

    public GetAllCoursesViewModelFactory(GetAllCoursesUseCase getAllCoursesUseCase) {
        this.getAllCoursesUseCase = getAllCoursesUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GetAllCoursesViewModel.class)) {
            return (T) new GetAllCoursesViewModel(getAllCoursesUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}