package com.example.projecctforandroidlessons.presentation;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.projecctforandroidlessons.MApplication;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.StudentsAdapter;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.StudentRepositoryImpl;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.AddStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.DeleteStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.FindStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.GetAllStudentsUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.UpdateStudentUseCase;

public class GetAllStudentsFragment extends Fragment {

    private StudentViewModel studentViewModel;
    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_all_students, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        studentsAdapter = new StudentsAdapter();
        recyclerView.setAdapter(studentsAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        studentViewModel.getStudents().observe(getViewLifecycleOwner(), students -> {
            // Update the UI
            studentsAdapter.setStudents(students);
        });

        // Fetch the students from the database
        studentViewModel.fetchStudents();
    }
}