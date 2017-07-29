package com.scs.university.course.registration.service;

import com.scs.university.course.registration.model.Student;

import java.util.Set;

public interface StudentService {

    Student getStudentById(Long id);
    Student findByEmailAddress(String emailAddresss);
    Set<Student> findAllStudents();
    void create(Student student);
    void update(Student student);
    void delete(Long id);

}
