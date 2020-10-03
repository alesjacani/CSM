package Accounts;

import java.util.ArrayList;
import java.util.List;

import Course.Courses;

import View.AdministratorView;

public class Professor  {
	
	private String firstNameProf;
	private String lastNameProf;
	private String usernameProf;
	private String passwordProf;
	private int idProfessor;
	
	
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
	public String getUsernameProf() {
		return usernameProf;
	}
	public void setUsernameProf(String userNameProf) {
		this.usernameProf = userNameProf;
	}
	public String getPasswordProf() {
		return passwordProf;
	}
	public void setPasswordProf(String passwordProf) {
		this.passwordProf = passwordProf;
	}

	
	
	
	public int getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstNameProf == null) ? 0 : firstNameProf.hashCode());
		result = prime * result + ((lastNameProf == null) ? 0 : lastNameProf.hashCode());
		result = prime * result + ((passwordProf == null) ? 0 : passwordProf.hashCode());
		result = prime * result + ((usernameProf == null) ? 0 : usernameProf.hashCode());
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
		if (usernameProf == null) {
			if (other.usernameProf != null)
				return false;
		} else if (!usernameProf.equals(other.usernameProf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor [firstNameProf=" + firstNameProf + ", lastNameProf=" + lastNameProf + ", userNameProf="
				+ usernameProf + ", passwordProf=" + passwordProf + "]";
	}

	
	
	//methods for addingStudents(name) Plus kursin qe ben pjese
	//method for setGrade();

}
	
	  
	





