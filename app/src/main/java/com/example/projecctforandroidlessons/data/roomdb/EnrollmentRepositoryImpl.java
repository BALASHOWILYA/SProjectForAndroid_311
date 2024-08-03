package com.example.projecctforandroidlessons.data.roomdb;


import com.example.projecctforandroidlessons.domain.models.EnrollmentDomain;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentRepositoryImpl {
    private final EnrollmentDao enrollmentDao;

    public EnrollmentRepositoryImpl(EnrollmentDao enrollmentDao) {
        this.enrollmentDao = enrollmentDao;
    }

    public void insert(EnrollmentDomain enrollmentDomain) {
        // Вызываем метод из слоя данных
        new Thread(() -> enrollmentDao.insert(Enrollment.fromDomain(enrollmentDomain))).start();
    }

    public List<EnrollmentDomain> getAllEnrollments() {
        // Вызываем метод из слоя данных и конвертируем его в доменные модели
        List<Enrollment> enrollments = enrollmentDao.getAllEnrollments();

        return enrollments.stream()
                .map(Enrollment::toDomain)
                .collect(Collectors.toList());
    }
}