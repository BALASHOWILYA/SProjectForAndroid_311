package com.example.projecctforandroidlessons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.Course;
import com.example.projecctforandroidlessons.data.roomdb.EnrollmentRepository;
import com.example.projecctforandroidlessons.data.roomdb.StudentRepository;
import com.example.projecctforandroidlessons.domain.EnrollmentDomain;
import com.example.projecctforandroidlessons.domain.StudentDomain;

import java.util.List;

public class StudentViewModel extends ViewModel {
    private final StudentRepository repository;
    private final MutableLiveData<List<StudentDomain>> studentLiveData = new MutableLiveData<>();

    public StudentViewModel(StudentRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<StudentDomain>> getEnrollments() {
        return studentLiveData;
    }

    public void fetchEnrollments() {
        new Thread(() -> {


            List<StudentDomain> students = repository.getAllStudents();
            studentLiveData.postValue(students);
        }).start();
    }

    public void addStudent(StudentDomain studentDomain) {
        repository.insert(studentDomain);
    }
}