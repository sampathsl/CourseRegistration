package com.scs.university.course.registration.service;

import com.scs.university.course.registration.model.Student;
import com.scs.university.course.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student findByEmailAddress(String emailAddresss) {
        return studentRepository.findByEmailAddress(emailAddresss);
    }

    @Override
    public Set<Student> findAllStudents() {
        return new HashSet<>(studentRepository.findAll());
    }

    @Override
    public void create(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }

}
