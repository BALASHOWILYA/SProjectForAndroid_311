package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

public class UpdateStudentFragment extends Fragment {

    private EditText editTextSearchEmail;
    private EditText editTextStudentName;
    private EditText editTextEmail;
    private EditText editTextBirthDate;
    private Button buttonSearchStudent;
    private Button buttonUpdateStudent;

    private StudentViewModel studentViewModel;

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
        View view = inflater.inflate(R.layout.fragment_update_student, container, false);

        editTextSearchEmail = view.findViewById(R.id.edit_text_search_email);
        editTextStudentName = view.findViewById(R.id.edit_text_student_name);
        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextBirthDate = view.findViewById(R.id.edit_text_birth_date);
        buttonSearchStudent = view.findViewById(R.id.button_search_student);
        buttonUpdateStudent = view.findViewById(R.id.button_update_student);

        buttonSearchStudent.setOnClickListener(v -> searchStudent());
        buttonUpdateStudent.setOnClickListener(v -> updateStudent());

        return view;
    }

    private void searchStudent() {
        String email = editTextSearchEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getContext(), "Email is required to search", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            StudentDomain student = studentViewModel.findStudentByEmail(email);
            if (student != null) {
                getActivity().runOnUiThread(() -> {
                    editTextStudentName.setText(student.getName());
                    editTextEmail.setText(student.getEmail());
                    editTextBirthDate.setText(student.getBirthDate());
                    buttonUpdateStudent.setEnabled(true);
                });
            } else {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Student not found", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void updateStudent() {
        String name = editTextStudentName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String birthDate = editTextBirthDate.getText().toString().trim();
        String searchEmail = editTextSearchEmail.getText().toString().trim();

        if (searchEmail.isEmpty()) {
            Toast.makeText(getContext(), "Search email is required", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            StudentDomain student = studentViewModel.findStudentByEmail(searchEmail);
            if (student != null) {
                student.setName(name);
                student.setEmail(email);
                student.setBirthDate(birthDate);
                studentViewModel.update(student);
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Student updated successfully", Toast.LENGTH_SHORT).show();
                });
            } else {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Student not found", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}
