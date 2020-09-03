package Accounts;

import java.util.ArrayList;
import java.util.List;

import Course.Courses;

import View.AdministratorView;

public class Professor  {
	
	private String firstNameProf;
	private String lastNameProf;
	private String userNameProf;
	private String passwordProf;

	//Courses [] professorCourses ;
	
	
	//Student [] students;
	//private Grade []grades;
	
	
	public Professor(String firstNameProf, String lastNameProf, String userNameProf, String passwordProf) {
		super();
		this.firstNameProf = firstNameProf;
		this.lastNameProf = lastNameProf;
		this.userNameProf = userNameProf;
		this.passwordProf = passwordProf;
	}

	public Professor() {
		// TODO Auto-generated constructor stub
	}
	public String getFirstNameProf() {
		return firstNameProf;
	}
	public void setFirstNameProf(String firstNameProf) {
		this.firstNameProf = firstNameProf;
	}
	public String getLastNameProf() {
		return lastNameProf;
	}
	public void setLastNameProf(String lastNameProf) {
		this.lastNameProf = lastNameProf;
	}
	public String getUserNameProf() {
		return userNameProf;
	}
	public void setUserNameProf(String userNameProf) {
		this.userNameProf = userNameProf;
	}
	public String getPasswordProf() {
		return passwordProf;
	}
	public void setPasswordProf(String passwordProf) {
		this.passwordProf = passwordProf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstNameProf == null) ? 0 : firstNameProf.hashCode());
		result = prime * result + ((lastNameProf == null) ? 0 : lastNameProf.hashCode());
		result = prime * result + ((passwordProf == null) ? 0 : passwordProf.hashCode());
		result = prime * result + ((userNameProf == null) ? 0 : userNameProf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (firstNameProf == null) {
			if (other.firstNameProf != null)
				return false;
		} else if (!firstNameProf.equals(other.firstNameProf))
			return false;
		if (lastNameProf == null) {
			if (other.lastNameProf != null)
				return false;
		} else if (!lastNameProf.equals(other.lastNameProf))
			return false;
		if (passwordProf == null) {
			if (other.passwordProf != null)
				return false;
		} else if (!passwordProf.equals(other.passwordProf))
			return false;
		if (userNameProf == null) {
			if (other.userNameProf != null)
				return false;
		} else if (!userNameProf.equals(other.userNameProf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor [firstNameProf=" + firstNameProf + ", lastNameProf=" + lastNameProf + ", userNameProf="
				+ userNameProf + ", passwordProf=" + passwordProf + "]";
	}

	
	
	//methods for addingStudents(name) Plus kursin qe ben pjese
	//method for setGrade();

}
	
	  
	





