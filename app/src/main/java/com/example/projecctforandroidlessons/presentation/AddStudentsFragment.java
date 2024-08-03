package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projecctforandroidlessons.MApplication;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.StudentRepositoryImpl;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.AddStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.DeleteStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.FindStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.GetAllStudentsUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.UpdateStudentUseCase;

public class AddStudentsFragment extends Fragment {

    private EditText editTextStudentName;
    private EditText editTextStudentEmail;
    private EditText editTextBirthDate;
    private TextView resultTextView;

    private StudentViewModel studentViewModel;

    private void init(View view) {
        editTextStudentName = view.findViewById(R.id.edit_text_student_name);
        editTextStudentEmail = view.findViewById(R.id.edit_text_email);
        editTextBirthDate = view.findViewById(R.id.edit_text_birth_date);
        resultTextView = view.findViewById(R.id.is_result_view_text_id);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = MApplication.getDatabase();
        StudentRepositoryImpl repository = new StudentRepositoryImpl(database.studentDao());

        // Initialize the use cases
        AddStudentUseCase addStudentUseCase = new AddStudentUseCase(repository);
        UpdateStudentUseCase updateStudentUseCase = new UpdateStudentUseCase(repository);
        FindStudentUseCase findStudentUseCase = new FindStudentUseCase(repository);
        GetAllStudentsUseCase getAllStudentsUseCase = new GetAllStudentsUseCase(repository);
        DeleteStudentUseCase deleteStudentUseCase = new DeleteStudentUseCase(repository);

        // Initialize the ViewModel
        studentViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new StudentViewModel(addStudentUseCase, updateStudentUseCase, findStudentUseCase, getAllStudentsUseCase, deleteStudentUseCase );
            }
        }).get(StudentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        view.findViewById(R.id.button_add_student).setOnClickListener(v -> addStudent());
    }

    private void addStudent() {
        String name = editTextStudentName.getText().toString();
        String email = editTextStudentEmail.getText().toString();
        String birthDate = editTextBirthDate.getText().toString();

        StudentDomain student = new StudentDomain(name, email, birthDate);

        new Thread(() -> {
            studentViewModel.insert(student);
        }).start();
    }
}