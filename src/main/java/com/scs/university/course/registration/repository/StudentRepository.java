package com.scs.university.course.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scs.university.course.registration.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmailAddress(String emailAddresss);

}
