package com.example.projecctforandroidlessons.data.roomdb;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.projecctforandroidlessons.domain.models.EnrollmentDomain;

import java.util.Date;



@Entity(tableName = "enrollments",
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class,
                        parentColumns = "id",
                        childColumns = "courseId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int studentId;
    private int courseId;
    private Date enrollmentDate;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    // Конвертеры
    public static Enrollment fromDomain(EnrollmentDomain domain) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(domain.getStudentId());
        enrollment.setCourseId(domain.getCourseId());
        enrollment.setEnrollmentDate(domain.getEnrollmentDate());
        return enrollment;
    }

    public EnrollmentDomain toDomain() {
        return new EnrollmentDomain(studentId, courseId, enrollmentDate);
    }
}
