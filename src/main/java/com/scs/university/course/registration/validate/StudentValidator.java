package com.scs.university.course.registration.validate;

import com.scs.university.course.registration.model.Student;
import com.scs.university.course.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class StudentValidator implements Validator {

    @Autowired
    private StudentService studentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Student student = (Student) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");

        if (student.getForName().length() < 3 || student.getForName().length() > 50) {
            errors.rejectValue("forName", "Size.courseRegistrationForm.forName");
        }

        if (student.getSurName().length() < 3 || student.getSurName().length() > 100) {
            errors.rejectValue("surName", "Size.courseRegistrationForm.surName");
        }

        if (student.getEmailAddress().length() < 3 || student.getEmailAddress().length() > 100) {
            errors.rejectValue("emailAddress", "Size.courseRegistrationForm.emailAddress");
        }

        Student studentTemp = studentService.findByEmailAddress(student.getEmailAddress());
        if (studentTemp != null && !studentTemp.getId().equals(student.getId())) {
            errors.rejectValue("emailAddress", "Duplicate.courseRegistrationForm.emailAddress");
        }

    }

}
