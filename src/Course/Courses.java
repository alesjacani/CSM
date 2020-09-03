package Course;

import java.util.ArrayList;
import java.util.List;

import Accounts.Professor;
import Accounts.Student;

public class Courses {
	
private String courseName;
private String courseId;
private String desciption;


public Courses(String name) {
	this.courseName =name;
}

private List<Professor> professorList = new ArrayList<>();
private List <Student> studentList = new ArrayList<>();


public List<Professor> getProfessorList() {
	return professorList;
}

public void setProfessorList(List<Professor> professorList) {
	this.professorList = professorList;
}

public List<Student> getStudentList() {
	return studentList;
}

public void setStudentList(List<Student> studentList) {
	this.studentList = studentList;
}





public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public String getCourseId() {
	return courseId;
}

public void setCourseId(String courseId) {
	this.courseId = courseId;
}


public String getDesciption() {
	return desciption;
}

public void setDesciption(String desciption) {
	this.desciption = desciption;
}


}
