package com.example.projecctforandroidlessons.data.roomdb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);


    // INNER JOIN
    @Query("SELECT students.name AS studentName, " +
            "courses.name AS courseName, " +
            "enrollments.enrollmentDate AS enrollmentDate " +
            "FROM enrollments " +
            "JOIN students ON enrollments.studentId = students.id " +
            "JOIN courses ON enrollments.courseId = courses.id")
    List<StudentWithCourses> getStudentsWithCourses();

    @Query("SELECT students.name AS studentName, " +
            "courses.name AS courseName, " +
            "enrollments.enrollmentDate AS enrollmentDate " +
            "FROM enrollments " +
            "LEFT JOIN students ON enrollments.studentId = students.id " +
            "LEFT JOIN courses ON enrollments.courseId = courses.id")
    List<StudentWithCourses> getStudentsWithCoursesLeftJoin();

    // Новый метод для получения всех записей из таблицы enrollments
    @Query("SELECT * FROM enrollments")
    List<Enrollment> getAllEnrollments();
}
