package Accounts;

import Course.Courses;

public class Student {
	private String firstNameStudent;
	private String lastNameStudent;
	private String userNameStudent;
	private String passwordStudent;
	
	
	private int idStudent;

	
	
	
	
	
	
	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public Student(String firstNameStudent, String lastNameStudent, String userNameStudent, String passwordStudent) {
		super();
		this.firstNameStudent = firstNameStudent;
		this.lastNameStudent = lastNameStudent;
		this.userNameStudent = userNameStudent;
		this.passwordStudent = passwordStudent;
	}

	public String getFirstNameStudent() {
		return firstNameStudent;
	}

	public void setFirstNameStudent(String firstNameStudent) {
		this.firstNameStudent = firstNameStudent;
	}

	public String getLastNameStudent() {
		return lastNameStudent;
	}

	public void setLastNameStudent(String lastNameStudent) {
		this.lastNameStudent = lastNameStudent;
	}

	public String getUserNameStudent() {
		return userNameStudent;
	}

	public void setUserNameStudent(String userNameStudent) {
		this.userNameStudent = userNameStudent;
	}

	public String getPasswordStudent() {
		return passwordStudent;
	}

	public void setPasswordStudent(String passwordStudent) {
		this.passwordStudent = passwordStudent;
	}


	
	

	 
	

}
