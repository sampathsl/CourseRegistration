package com.scs.university.course.registration.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course implements Serializable ,  Comparable<Course>{

	private static final long serialVersionUID = 121021254535L;
	
	private Long id;
	private String courseName;
	private Set<Student> students = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@ManyToMany(mappedBy = "courses",fetch= FetchType.EAGER)
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public int compareTo(Course o) {
	    Long diff = this.id - o.getId();
		return diff.intValue();
	}

}
