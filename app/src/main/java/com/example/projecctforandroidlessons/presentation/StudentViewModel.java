package com.example.projecctforandroidlessons.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.AddStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.DeleteStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.FindStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.GetAllStudentsUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.UpdateStudentUseCase;

import java.util.List;

public class StudentViewModel extends ViewModel {
    private final AddStudentUseCase addStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final FindStudentUseCase findStudentUseCase;
    private final GetAllStudentsUseCase getAllStudentsUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final MutableLiveData<List<StudentDomain>> studentLiveData = new MutableLiveData<>();



    public StudentViewModel(AddStudentUseCase addStudentUseCase, UpdateStudentUseCase updateStudentUseCase, FindStudentUseCase findStudentUseCase, GetAllStudentsUseCase getAllStudentsUseCase, DeleteStudentUseCase deleteStudentUseCase) {
        this.addStudentUseCase = addStudentUseCase;
        this.updateStudentUseCase = updateStudentUseCase;
        this.findStudentUseCase = findStudentUseCase;
        this.getAllStudentsUseCase = getAllStudentsUseCase;
        this.deleteStudentUseCase = deleteStudentUseCase;
    }

    public LiveData<List<StudentDomain>> getStudents() {
        return studentLiveData;
    }






    public void fetchStudents() {
        new Thread(() -> {
            List<StudentDomain> students = getAllStudentsUseCase.execute();
            studentLiveData.postValue(students);
        }).start();
    }









    public void insert(StudentDomain studentDomain) {
        new Thread(() -> {
            addStudentUseCase.execute(studentDomain);
            fetchStudents(); // Обновление списка студентов после добавления
        }).start();
    }




    public void update(StudentDomain studentDomain) {
        new Thread(() -> {
            updateStudentUseCase.execute(studentDomain);
            fetchStudents(); // Обновление списка студентов после обновления
        }).start();
    }

    public StudentDomain findStudentByEmail(String email) {
        return findStudentUseCase.execute(email);
    }



    public void deleteStudentByEmail(String email) {
        new Thread(() -> {
            deleteStudentUseCase.execute(email);
            fetchStudents(); // Обновление списка студентов после удаления
        }).start();
    }




}