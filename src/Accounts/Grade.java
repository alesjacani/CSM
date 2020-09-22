package Accounts;

import java.util.ArrayList;
import java.util.List;

import Course.Courses;

public class Grade {
	private List<Student> studentList = new ArrayList<>();
    private List<Courses> courseList = new ArrayList<>();
    private int grade;
	
	
   



	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Courses> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Courses> courseList) {
		this.courseList = courseList;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	
}
