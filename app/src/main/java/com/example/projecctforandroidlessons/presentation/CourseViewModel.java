package com.example.projecctforandroidlessons.presentation;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projecctforandroidlessons.data.roomdb.provider.CourseContentProvider;
import com.example.projecctforandroidlessons.data.roomdb.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Course>> coursesLiveData = new MutableLiveData<>();

    public CourseViewModel(Application application) {
        super(application);
        loadCourses();
    }

    public LiveData<List<Course>> getCourses() {
        return coursesLiveData;
    }

    public void loadCourses() {
        new Thread(() -> {
            Cursor cursor = getApplication().getContentResolver().query(CourseContentProvider.CONTENT_URI, null, null, null, null);
            List<Course> courses = new ArrayList<>();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Course course = new Course();
                    course.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    course.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    course.setCourseCode(cursor.getInt(cursor.getColumnIndexOrThrow("courseCode")));
                    course.setCredits(cursor.getInt(cursor.getColumnIndexOrThrow("credits")));
                    courses.add(course);
                }
                cursor.close();
            }
            coursesLiveData.postValue(courses);
        }).start();
    }

    public void insertCourse(Course course) {
        new Thread(() -> {
            ContentValues values = new ContentValues();
            values.put("name", course.getName());
            values.put("courseCode", course.getCourseCode());
            values.put("credits", course.getCredits());
            getApplication().getContentResolver().insert(CourseContentProvider.CONTENT_URI, values);
            loadCourses();
        }).start();
    }

    public void deleteCourse(int id) {
        new Thread(() -> {
            Uri uri = Uri.withAppendedPath(CourseContentProvider.CONTENT_URI, String.valueOf(id));
            getApplication().getContentResolver().delete(uri, null, null);
            loadCourses();
        }).start();
    }

    public void updateCourse(Course course) {
        new Thread(() -> {
            ContentValues values = new ContentValues();
            values.put("name", course.getName());
            values.put("courseCode", course.getCourseCode());
            values.put("credits", course.getCredits());
            Uri uri = Uri.withAppendedPath(CourseContentProvider.CONTENT_URI, String.valueOf(course.getId()));
            getApplication().getContentResolver().update(uri, values, null, null);
            loadCourses();
        }).start();
    }
}

