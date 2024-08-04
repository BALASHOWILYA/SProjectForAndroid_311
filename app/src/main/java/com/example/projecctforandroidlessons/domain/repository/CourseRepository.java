package com.example.projecctforandroidlessons.domain.repository;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projecctforandroidlessons.data.roomdb.Course;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;

import java.util.List;

public interface CourseRepository {

    void insert(CourseDomain course);

    void update(CourseDomain course);

    void delete(String name);

    List<CourseDomain> getAllCourses();
}


