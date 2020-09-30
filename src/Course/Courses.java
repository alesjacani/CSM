package Course;

import java.util.ArrayList;
import java.util.List;

import Accounts.Professor;
import Accounts.Student;

public class Courses {
	
private String courseName;
private int courseId;
private String desciption;
private String durationTime;


private List<Professor> professorList = new ArrayList<>();
private int professorId;

private List<Student> studentList = new ArrayList<>();
public int getProfessorId() {
	return professorId;
}


public void setProfessorId(int professorId) {
	this.professorId = professorId;
}


public Courses() {
	super();

}


public String getDurationTime() {
	return durationTime;
}

public void setDurationTime(String durationTime) {
	this.durationTime = durationTime;
}




public List<Student> getStudentList() {
	return studentList;
}


public void setStudentList(List<Student> studentList) {
	this.studentList = studentList;
}


public List<Professor> getProfessorList() {
	return professorList;
}


public void setProfessorList(List<Professor> professorList) {
	this.professorList = professorList;
}


public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public int getCourseId() {
	return courseId;
}

public void setCourseId(int courseId) {
	this.courseId=courseId;
}


public String getDesciption() {
	return desciption;
}

public void setDesciption(String desciption) {
	this.desciption = desciption;
}


}
