package com.scs.university.course.registration.service;

import com.scs.university.course.registration.model.Course;

import java.util.Set;

public interface CourseService {

    Course getCourseById(Long id);
    Course findByCourseName(String courseName);
    Set<Course> findAllCourses();
    void create(Course course);
    void update(Course course);
    void delete(Long id);

}
