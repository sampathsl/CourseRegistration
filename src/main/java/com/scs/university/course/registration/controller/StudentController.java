package com.scs.university.course.registration.controller;

import com.scs.university.course.registration.model.Course;
import com.scs.university.course.registration.service.CourseService;
import com.scs.university.course.registration.service.StudentService;
import com.scs.university.course.registration.validate.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scs.university.course.registration.model.Student;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private StudentValidator studentValidator;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(
            @RequestParam(value = "id", required = false) Long id,
            Model model
        ) {

        Student student = null;
        Set<String> courseStrSet = new TreeSet<>();
        Set<Course> courseSet = new TreeSet<>();
        Set<Course> selectedCourseSet = new TreeSet<>();
        List<String> selectedCourseListStr = new ArrayList<>();

        if( id != null ) {
            student = studentService.getStudentById(id);
            selectedCourseSet = student.getCourses();
            System.out.println("selectedCourseSet size :: " + selectedCourseSet.size());
            for ( Course course : selectedCourseSet ) {
                selectedCourseListStr.add(course.getCourseName());
            }
        } else {
            student = new Student();
            student.setCourses(new TreeSet<>(courseService.findAllCourses()));
        }

        model.addAttribute("courseRegistrationForm", student);
        fillModel(false, model, courseSet, courseStrSet,selectedCourseListStr , false);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(
            @ModelAttribute("courseRegistrationForm") Student courseRegistrationForm,
            @RequestParam(value="courselist", required = false) List<String> selectedCourseList,
            BindingResult bindingResult,
            Model model
    ) {

	    Set<Course> courseSet = new TreeSet<>();
        Set<String> courseStrSet = new TreeSet<>();
        boolean hasError = false;
	    if( selectedCourseList == null ) {
            hasError = true;
            model.addAttribute("courseListError","Please select at least one course!");
        }

    	studentValidator.validate(courseRegistrationForm, bindingResult);

        fillModel(true, model, courseSet, courseStrSet , selectedCourseList , hasError);

        if (bindingResult.hasErrors() || hasError) {
            return "registration";
        }

        if(courseRegistrationForm.getId() == null) {
            courseRegistrationForm.setCourses(courseSet);
            studentService.create(courseRegistrationForm);
            model.addAttribute("id",courseRegistrationForm.getId());
        } else {
            Student student = studentService.getStudentById(courseRegistrationForm.getId());
            student.setForName(courseRegistrationForm.getForName());
            student.setSurName(courseRegistrationForm.getSurName());
            student.setEmailAddress(courseRegistrationForm.getEmailAddress());
            student.setCourses(courseSet);
            studentService.update(student);
            model.addAttribute("id",student.getId());
        }

        return "redirect:/registration";
    }

    private void fillModel(boolean isPost, Model model, Set<Course> courseSet,
                           Set<String> courseCheckedSetStr, List<String> selectedCourseList, boolean hasError) {

        Set<Course> allCourseSet = courseService.findAllCourses();
        Set<String> allCourseList = new TreeSet<>();
        for ( Course course : allCourseSet ) {
            allCourseList.add(course.getCourseName());
        }

        model.addAttribute("allCourseList", allCourseList);

        if( courseCheckedSetStr != null && selectedCourseList != null) {

            if(selectedCourseList.size() > 0) {
                for(String courseStr : selectedCourseList) {
                    Course course = courseService.findByCourseName(courseStr);
                    if( course != null ) {
                        if (courseSet != null ) {
                            courseSet.add(course);
                        }
                        courseCheckedSetStr.add(course.getCourseName());
                    }
                }
            } else {
                if(isPost) {
                    hasError = true;
                    model.addAttribute("courseListError","Please select at least one course!");
                }
            }

            model.addAttribute("courseCheckedSet",courseCheckedSetStr);

        }

    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "redirect:/registration";
    }
	
}
