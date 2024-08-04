package com.example.projecctforandroidlessons.data.roomdb;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long  insert(Course course);

    @Update
    int  update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();


    @Query("SELECT * FROM courses")
    Cursor getAllCursor();

    @Query("SELECT * FROM courses WHERE id = :id")
    Cursor getCourseByIdCursor(long id);

    @Query("DELETE FROM courses WHERE id = :id")
    int deleteById(long id);
}

