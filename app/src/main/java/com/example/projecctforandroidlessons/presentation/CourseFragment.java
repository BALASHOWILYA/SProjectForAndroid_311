package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.data.roomdb.Course;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;

import java.util.List;
import java.util.stream.Collectors;

public class CourseFragment extends Fragment {

    private CourseViewModel courseViewModel;
    private CoursesAdapter coursesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_all_courses, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        coursesAdapter = new CoursesAdapter();
        recyclerView.setAdapter(coursesAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourses().observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                // Convert entity list to domain model list
                List<CourseDomain> courseDomains = courses.stream()
                        .map(course -> new CourseDomain(course.getName(), course.getCourseCode(), course.getCredits()))
                        .collect(Collectors.toList());
                coursesAdapter.setCourses(courseDomains);
            }
        });

        coursesAdapter.setOnItemClickListener(new CoursesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CourseDomain course) {
                // Handle the item click event here
                // Example: Toast.makeText(getContext(), "Clicked: " + course.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        View addCourseButton = view.findViewById(R.id.add_course_button);
        if (addCourseButton != null) {
            addCourseButton.setOnClickListener(v -> {
                Course newCourse = new Course();
                newCourse.setName("New Course");
                newCourse.setCourseCode(123);
                newCourse.setCredits(3);
                courseViewModel.insertCourse(newCourse);
            });
        } else {
            // Log or handle the case when the button is not found
        }
    }
}
