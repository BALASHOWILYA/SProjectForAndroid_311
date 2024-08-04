package com.example.projecctforandroidlessons;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.CourseRepositoryImpl;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.usecase.courseusecase.GetAllCoursesUseCase;
import com.example.projecctforandroidlessons.presentation.CoursesAdapter;
import com.example.projecctforandroidlessons.presentation.GetAllCoursesViewModel;
import com.example.projecctforandroidlessons.presentation.GetAllCoursesViewModelFactory;

import java.util.List;

public class GetAllCoursesFragment extends Fragment {

    private GetAllCoursesViewModel getAllCoursesViewModel;
    private RecyclerView recyclerView;
    private CoursesAdapter coursesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase database = MApplication.getDatabase();
        CourseRepositoryImpl repository = new CourseRepositoryImpl(database.courseDao());
        GetAllCoursesUseCase getAllCoursesUseCase = new GetAllCoursesUseCase(repository);
        GetAllCoursesViewModelFactory factory = new GetAllCoursesViewModelFactory(getAllCoursesUseCase);
        getAllCoursesViewModel = new ViewModelProvider(this, factory).get(GetAllCoursesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_all_courses, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        coursesAdapter = new CoursesAdapter();
        recyclerView.setAdapter(coursesAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllCoursesViewModel.getCourses().observe(getViewLifecycleOwner(), new Observer<List<CourseDomain>>() {
            @Override
            public void onChanged(List<CourseDomain> courses) {
                coursesAdapter.setCourses(courses);
            }
        });
        getAllCoursesViewModel.loadCourses();
    }
}
