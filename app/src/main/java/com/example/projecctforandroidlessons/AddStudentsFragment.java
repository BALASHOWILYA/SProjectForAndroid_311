package com.example.projecctforandroidlessons;

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

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.EnrollmentRepository;
import com.example.projecctforandroidlessons.data.roomdb.StudentDao;
import com.example.projecctforandroidlessons.data.roomdb.StudentRepository;
import com.example.projecctforandroidlessons.domain.StudentDomain;
import com.example.projecctforandroidlessons.ui.EnrollmentViewModel;


public class AddStudentsFragment extends Fragment {

    private EditText editTextStudentName;
    private EditText editTextStudentEmail;
    private EditText editTextBirthDate;

    private StudentDao studentDao;

    private StudentViewModel studentViewModel;


    private  void init(View view){
        editTextStudentName = view.findViewById(R.id.edit_text_student_name);
        editTextStudentName = view.findViewById(R.id.edit_text_email);
        editTextStudentName = view.findViewById(R.id.edit_text_birth_date);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the database and repository
        // AppDatabase database = AppDatabase.getInstance(requireContext());
        MApplication application = new MApplication();
        AppDatabase database = application.getDatabase();
        StudentRepository repository = new StudentRepository(database.studentDao());

        // Initialize the ViewModel
        studentViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new StudentViewModel(repository);
            }
        }).get(StudentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void addStudent(){
        String name = editTextStudentName.getText().toString();
        String email = editTextStudentName.getText().toString();
        String birth_date = editTextStudentName.getText().toString();

        StudentDomain student = new StudentDomain(name, email, birth_date );

        new Thread(()-> {
            try {
                studentDao.insert(student);
            }
            catch (Exception e){
                e.toString();
            }



        }).start();

    }
}