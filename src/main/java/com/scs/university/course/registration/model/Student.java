package com.scs.university.course.registration.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1121212121452L;
	
	private Long id;
	private String forName;
	private String surName;
	private String emailAddress;
	private Set<Course> courses = new HashSet<>(5);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForName() {
		return forName;
	}

	public void setForName(String forName) {
		this.forName = forName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", forName='" + forName + '\'' +
                ", surName='" + surName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", courses=" + courses +
                '}';
    }

}
