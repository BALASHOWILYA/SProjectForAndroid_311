package com.example.projecctforandroidlessons.data.roomdb;

import com.example.projecctforandroidlessons.domain.models.CourseDomain;
import com.example.projecctforandroidlessons.domain.repository.CourseRepository;

import java.util.Collections;
import java.util.List;

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
    public void update(CourseDomain course) {

    }

    @Override
    public void delete(String course) {

    }

    @Override
    public List<CourseDomain> getAllCourses() {
        return Collections.emptyList();
    }
}
