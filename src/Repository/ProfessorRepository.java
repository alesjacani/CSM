package Repository;

import java.util.List;

import javax.print.attribute.standard.PresentationDirection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Accounts.Grade;
import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Util.ConnectionDb;

public class ProfessorRepository {
private String CHANGE_PASSWORD="UPDATE professor SET professor_password=? WHERE professor_username=?";

private String ADD_STUDENT="INSERT INTO student (student_name, student_surname,student_username,student_password) VALUES (?,?,?,?)";
private String ADD_STUDENT_COURSE = "INSERT INTO grade (student_id,course_id) VALUES (?,?)";

private final String STUDENT_EXIST="SELECT COUNT (*) FROM  student WHERE student_username=?";

private final String GET_PROF_BY_COURSE_ID="SELECT * FROM professor INNER JOIN course ON professor.professor_id=course.professor_id WHERE course_name=?";

private final String GET_COURSE_NAME_BY_PROFESSOR_USERNAME="SELECT course.course_name FROM course INNER JOIN professor ON course.professor_id= professor.professor_id WHERE professor.professor_username=?";

private final String UPDATE_COURSE_DETAILS="UPDATE course SET course_description=?,course_duration_time=? WHERE course_name=?";
private final String GRADE_STUDENT="UPDATE grade SET course_grade=? WHERE course_id=? AND student_id=?";
private final String GET_STUDENT_BY_USERNAME="SELECT * FROM student WHERE student_username=?";

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




public Student getStudentByUsername(Student student) {
	//	List <Professor> professors = new ArrayList();
			try (Connection connection = ConnectionDb.getConnection();
					PreparedStatement preparedSt = connection.prepareStatement(GET_STUDENT_BY_USERNAME);) {
				
				preparedSt.setString(1, student.getUserNameStudent());
				
				ResultSet rs = preparedSt.executeQuery();
			
				while (rs.next()) {
					
					student.setIdStudent(rs.getInt("student_id"));
					student.setFirstNameStudent(rs.getString("student_name"));
					student.setLastNameStudent(rs.getString("student_surname"));
					student.setUserNameStudent(rs.getString("student_username"));
					
					
				}
				return student;
				
			} catch (SQLException e) {
				System.out.println("error " + e);
				return null;
			}
		}//end of getProfessorByUsernameAndPassword
	



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

public Professor getProfByCourseName(String courseName) {
	try(Connection connection = ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(GET_PROF_BY_COURSE_ID);) {
		preparedSt.setString(1, courseName);
		ResultSet rs = preparedSt.executeQuery();
		Professor professor = new Professor();
		Courses course =new Courses();
		while (rs.next()) {
			
		    professor.setIdProfessor(rs.getInt("professor_id"));
			professor.setFirstNameProf(rs.getString("professor_name"));
			professor.setLastNameProf(rs.getString("professor_surname"));
			professor.setUsernameProf(rs.getString("professor_username"));
			
		//??? si mund ta marr kete direkte nga kjo metod
        //course.setCourseName(rs.getString("course_name")); ??
		}
		return professor;
	} catch (SQLException e) {
		System.out.println("error " + e);
		return null;
	}
}

public List<String> getCourseNameByProfessorUsername(String profUsername){
	List<String> name = new ArrayList();
	try(Connection connection = ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(GET_COURSE_NAME_BY_PROFESSOR_USERNAME);) {
		
		    preparedSt.setString(1, profUsername);
		    ResultSet rs = preparedSt.executeQuery();
		    Courses course=new Courses();
		    while (rs.next()) {
				
			 course.setCourseName(rs.getString("course_name"));
		     name.add(course.getCourseName());
			
			}
		  return  name;
	}catch (SQLException e) {
		System.out.println("error " + e);
		return null;
	}
}




public void editCourseDetails(String description, String time,String courseName) {
	try( Connection connection =ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(UPDATE_COURSE_DETAILS);) {
		preparedSt.setString(1, description);
		preparedSt.setString(2, time);
		preparedSt.setString(3, courseName);
		
		preparedSt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("error " + e);
		
	}
}


public void addGrade (Courses course, Student student,Grade grade) {
	try( Connection connection =ConnectionDb.getConnection();
	PreparedStatement preparedSt = connection.prepareStatement(GRADE_STUDENT);) {
		preparedSt.setInt(1, grade.getGrade());
		preparedSt.setInt(2, course.getCourseId() );
		preparedSt.setInt(3, student.getIdStudent());
		preparedSt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("error " + e);
		
	}

}

}//end of CLASS
