package com.example.projecctforandroidlessons.data.roomdb;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    
    @Insert
    long insert(Student student);

    @Update
    int update(Student student);

    @Delete
    int delete(Student student);

    @Query("SELECT * FROM students")
    List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE students.email = :emailStudent LIMIT 1")
    Student findStudentByEmail(String emailStudent);

    // Для ContentProvider
    @Query("SELECT * FROM students")
    Cursor getAllStudentsCursor();

    @Query("SELECT * FROM students WHERE id = :id LIMIT 1")
    Cursor getStudentByIdCursor(long id);

    @Query("DELETE FROM students WHERE id = :id")
    int deleteStudentById(long id);


}

