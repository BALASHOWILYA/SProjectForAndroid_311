package com.example.projecctforandroidlessons;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.CourseRepositoryImpl;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.usecase.courseusecase.AddCourseUseCase;
import com.example.projecctforandroidlessons.presentation.AddCourseViewModel;
import com.example.projecctforandroidlessons.presentation.AddCourseViewModelFactory;

public class AddCourseFragment extends Fragment {

    private AddCourseViewModel addCourseViewModel;
    private EditText courseNameEditText;
    private EditText courseCodeEditText;
    private EditText courseCreditsEditText;
    private Button addCourseButton;

    private void init(View view) {
        courseNameEditText = view.findViewById(R.id.course_name);
        courseCodeEditText = view.findViewById(R.id.course_code);
        courseCreditsEditText = view.findViewById(R.id.course_credits);
        addCourseButton = view.findViewById(R.id.add_course_button);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = MApplication.getDatabase();
        CourseRepositoryImpl repository = new CourseRepositoryImpl(database.courseDao());
        AddCourseUseCase addCourseUseCase = new AddCourseUseCase(repository);
        AddCourseViewModelFactory factory = new AddCourseViewModelFactory(addCourseUseCase);
        addCourseViewModel = new ViewModelProvider(this, factory).get(AddCourseViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        addCourseButton.setOnClickListener(v -> {
            String courseName = courseNameEditText.getText().toString();
            int courseCode = Integer.parseInt(courseCodeEditText.getText().toString());
            int credits = Integer.parseInt(courseCreditsEditText.getText().toString());

            CourseDomain courseDomain = new CourseDomain(courseName, courseCode, credits);
            addCourseViewModel.addCourse(courseDomain);
        });
    }
}
