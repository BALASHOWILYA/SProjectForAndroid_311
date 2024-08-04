package com.example.projecctforandroidlessons.data.roomdb;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseRepositoryImpl implements CourseRepository {

    private final CourseDao courseDao;

    public CourseRepositoryImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void insert(CourseDomain courseDomain) {
        new Thread(() -> courseDao.insert(Course.fromDomain(courseDomain))).start();
    }

    @Override
    public void update(CourseDomain courseDomain) {
        new Thread(() -> courseDao.update(Course.fromDomain(courseDomain))).start();
    }

    @Override
    public void delete(String courseName) {

    }

    @Override
    public List<CourseDomain> getAllCourses() {
        return courseDao.getAllCourses().stream()
                .map(Course::toDomain)
                .collect(Collectors.toList());
    }
}
