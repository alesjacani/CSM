package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Util.ConnectionDb;

public class ProfessorRepository {
private String CHANGE_PASSWORD="UPDATE professor SET professor_password=? WHERE professor_username=?";

private String ADD_STUDENT="INSERT INTO student (student_name, student_surname,student_username,student_password) VALUES (?,?,?,?)";
private String ADD_STUDENT_COURSE = "INSERT INTO grade (student_id,course_id) VALUES (?,?)";

private final String STUDENT_EXIST="SELECT COUNT (*) FROM  student WHERE student_username=?";
private final String GET_ID_STUDENT_FROM_USERNAME ="SELECT student_id FROM student WHERE student_username=?";

public void changeProfessorPassword(String username, String password) {
	try ( Connection connection =ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(CHANGE_PASSWORD);) {
		preparedSt.setString(1, password);
		preparedSt.setString(2, username);
		preparedSt.executeUpdate();
		
	} catch (SQLException e) {
		System.out.println("error " + e);
		
	}
}//end of changeProfessorPassword

public void addStudent(Student student) {
	try (Connection connection = ConnectionDb.getConnection();
			PreparedStatement preparedst = connection.prepareStatement(ADD_STUDENT);){
		preparedst.setString(1, student.getFirstNameStudent());
		preparedst.setString(2, student.getLastNameStudent());
		preparedst.setString(3, student.getUserNameStudent());
		preparedst.setString(4,student.getPasswordStudent());
		
		preparedst.executeUpdate();
		
	} catch (SQLException e) {
		System.out.println("error " + e);
	}
}//end of addStudent
public boolean studentExists(Student student) {
	
	try(Connection connection = ConnectionDb.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(STUDENT_EXIST)) {
		preparedStatement.setString(1, student.getUserNameStudent());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			if(rs.getInt(1)==0) {
				 return false;
			}
			else {
				return true;
			}
		}
		
		
	} catch (SQLException e) {
		System.out.println("error " + e);
	}
	return false;
}//end of studentExists

public int getStudentIdByUsername (Student s) {
	try (Connection connection =ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(GET_ID_STUDENT_FROM_USERNAME);){
		
	    preparedSt.setString(1, s.getUserNameStudent());  
	    ResultSet rs = preparedSt.executeQuery();
	
	    
	    while(rs.next()) {
	    	s.setIdStudent(rs.getInt("student_id"));
	  	
	    }
    return s.getIdStudent();
    
	} catch (SQLException e) {
		System.out.println("error " + e);
		return 0;
	}
}//end getStudentIdByUsername

public void addStudentCourseByIDs(int studentid, int courseId) {
	
	try(Connection connection =ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(ADD_STUDENT_COURSE);) {
		  preparedSt.setInt(1, studentid);
		  preparedSt.setInt(2, courseId);
		  preparedSt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("error " + e);
		
	}
}//end of addStudentCourseByIDs




}//end of CLASS
