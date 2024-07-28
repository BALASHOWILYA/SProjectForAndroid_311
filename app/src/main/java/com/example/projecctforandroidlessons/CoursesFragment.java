package com.example.projecctforandroidlessons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projecctforandroidlessons.ui.EnrollmentAdapter;
import com.example.projecctforandroidlessons.ui.EnrollmentViewModel;
import com.example.projecctforandroidlessons.data.roomdb.EnrollmentRepository;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.Converters;
import com.example.projecctforandroidlessons.data.roomdb.Course;
import com.example.projecctforandroidlessons.data.roomdb.Enrollment;
import com.example.projecctforandroidlessons.data.roomdb.Student;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CoursesFragment extends Fragment {

    private EnrollmentViewModel viewModel;

    private Button insert_students_courses_button;
    private Button get_enrollments_button;
    private Button get_data_button;

    private RecyclerView recyclerView;
    private EnrollmentAdapter adapter;

    private void init(View view){
        insert_students_courses_button = view.findViewById(R.id.insert_courses_students_button_id);
        get_enrollments_button = view.findViewById(R.id.get_enrollments_button_id);
        get_data_button = view.findViewById(R.id.get_data_button_id);
        recyclerView = view.findViewById(R.id.recyclerViewEnrollments);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the database and repository
       // AppDatabase database = AppDatabase.getInstance(requireContext());
        MApplication application = new MApplication();
        AppDatabase database = application.getDatabase();
        EnrollmentRepository repository = new EnrollmentRepository(database.enrollmentDao());

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new EnrollmentViewModel(repository);
            }
        }).get(EnrollmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        insert_students_courses_button.setOnClickListener(v -> insertDemoData());

        get_enrollments_button.setOnClickListener(v -> {
            viewModel.fetchEnrollments();
        });

        get_data_button.setOnClickListener(v -> displayData());

        viewModel.getEnrollments().observe(getViewLifecycleOwner(), enrollments -> {
            if (adapter == null) {
                adapter = new EnrollmentAdapter(enrollments);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.updateEnrollments(enrollments);
            }
        });
    }

    private void displayData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                AppDatabase database = AppDatabase.getInstance(requireContext());
                List<Student> students = database.studentDao().getAllStudents();
                List<Course> courses = database.courseDao().getAllCourses();
                List<Enrollment> enrollments = database.enrollmentDao().getAllEnrollments();

                requireActivity().runOnUiThread(() -> {
                    for (Student student : students) {
                        Log.d("StudentInfo", "ID: " + student.getId() + ", Name: " + student.getName());
                    }
                    for (Course course : courses) {
                        Log.d("CourseInfo", "ID: " + course.getId() + ", Name: " + course.getName());
                    }
                    for (Enrollment enrollment : enrollments) {
                        Log.d("EnrollmentInfo", "StudentID: " + enrollment.getStudentId() + ", CourseID: " + enrollment.getCourseId() + ", Date: " + enrollment.getEnrollmentDate());
                    }
                });
            } catch (Exception e) {
                Log.e("DatabaseFetchError", "Error fetching data", e);
            }
        });
    }

    private void insertDemoData() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                AppDatabase database = AppDatabase.getInstance(requireContext());

                // Insert students
                Student student1 = new Student();
                student1.setName("John Doe");
                student1.setEmail("john@example.com");
                student1.setBirthDate("1995-03-01");
                long student1Id = database.studentDao().insert(student1);

                Student student2 = new Student();
                student2.setName("Jane Smith");
                student2.setEmail("jane@example.com");
                student2.setBirthDate("1995-03-01");
                long student2Id = database.studentDao().insert(student2);

                // Insert courses
                Course course1 = new Course();
                course1.setName("Mathematics");
                course1.setCourseCode(101);
                long course1Id = database.courseDao().insert(course1);

                Course course2 = new Course();
                course2.setName("History");
                course2.setCourseCode(102);
                long course2Id = database.courseDao().insert(course2);

                // Insert enrollments
                insertEnrollments((int) student1Id, (int) course1Id, (int) student2Id, (int) course2Id);

                Log.d("DatabaseInsert", "Demo data successfully inserted.");
            } catch (Exception e) {
                Log.e("DatabaseInsertError", "Error inserting demo data", e);
            }
        });
    }

    private void insertEnrollments(int student1Id, int course1Id, int student2Id, int course2Id) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                AppDatabase database = AppDatabase.getInstance(requireContext());

                // Insert enrollments
                Enrollment enrollment1 = new Enrollment();
                enrollment1.setStudentId(student1Id);
                enrollment1.setCourseId(course1Id);
                String inputDate = "2024-07-28";
                Date date1 = Converters.fromString(inputDate);
                enrollment1.setEnrollmentDate(date1);
                database.enrollmentDao().insert(enrollment1);

                Enrollment enrollment2 = new Enrollment();
                enrollment2.setStudentId(student2Id);
                enrollment2.setCourseId(course2Id);
                Date date2 = Converters.fromString(inputDate);
                enrollment2.setEnrollmentDate(date2);
                database.enrollmentDao().insert(enrollment2);

                Log.d("DatabaseInsert", "Enrollments successfully inserted.");
            } catch (Exception e) {
                Log.e("DatabaseInsertError", "Error inserting enrollments", e);
            }
        });
    }
}
