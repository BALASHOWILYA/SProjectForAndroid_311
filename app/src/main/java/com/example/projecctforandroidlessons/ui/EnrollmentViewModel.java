package com.example.projecctforandroidlessons.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.Course;
import com.example.projecctforandroidlessons.data.roomdb.EnrollmentRepository;
import com.example.projecctforandroidlessons.domain.EnrollmentDomain;

import java.util.List;

public class EnrollmentViewModel extends ViewModel {
    private final EnrollmentRepository repository;
    private final MutableLiveData<List<EnrollmentDomain>> enrollmentsLiveData = new MutableLiveData<>();

    public EnrollmentViewModel(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<EnrollmentDomain>> getEnrollments() {
        return enrollmentsLiveData;
    }

    public void fetchEnrollments() {
        new Thread(() -> {


            List<EnrollmentDomain> enrollments = repository.getAllEnrollments();
            enrollmentsLiveData.postValue(enrollments);
        }).start();
    }

    public void addEnrollment(EnrollmentDomain enrollmentDomain) {
        repository.insert(enrollmentDomain);
    }
}