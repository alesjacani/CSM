package Accounts;

import Course.Courses;

public class Grade {

	private Courses courses;
	private Student students;
	private int grade;
	
	public Grade(Courses courses, Student students, int grade) {
		super();
		this.courses = courses;
		this.students = students;
		this.grade = grade;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public Student getStudents() {
		return students;
	}

	public void setStudents(Student students) {
		this.students = students;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	
}
