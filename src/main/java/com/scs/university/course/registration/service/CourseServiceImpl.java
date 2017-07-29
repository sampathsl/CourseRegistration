package com.scs.university.course.registration.service;

import com.scs.university.course.registration.model.Course;
import com.scs.university.course.registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findOne(id);
    }

    @Override
    public Course findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public Set<Course> findAllCourses() {
        return new HashSet<>(courseRepository.findAll());
    }

    @Override
    public void create(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(id);
    }

}
