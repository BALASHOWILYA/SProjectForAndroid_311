package com.example.projecctforandroidlessons.presentation;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.usecase.courseusecase.GetAllCoursesUseCase;

import java.util.List;



public class GetAllCoursesViewModel extends ViewModel {

    private final GetAllCoursesUseCase getAllCoursesUseCase;
    private final MutableLiveData<List<CourseDomain>> courses = new MutableLiveData<>();

    public GetAllCoursesViewModel(GetAllCoursesUseCase getAllCoursesUseCase) {
        this.getAllCoursesUseCase = getAllCoursesUseCase;
    }

    public LiveData<List<CourseDomain>> getCourses() {
        return courses;
    }

    public void loadCourses() {
        new Thread(() -> {
            List<CourseDomain> courseList = getAllCoursesUseCase.execute();
            courses.postValue(courseList);
        }).start();
    }

}