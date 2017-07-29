package com.scs.university.course.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scs.university.course.registration.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseName(String courseName);

}
