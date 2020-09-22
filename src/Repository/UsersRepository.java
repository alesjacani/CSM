package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Util.ConnectionDb;





public class UsersRepository {
	
	//*ADD, getIdbyName, DELETE for COURSE*
	private final String ADD_COURSE =  "INSERT INTO course (course_name) VALUES (?)";
    private final String GET_ID_OF_COURSE= "SELECT course_id FROM course WHERE course_name=?";
    private final String DELETE_COURSE = "DELETE FROM course WHERE course_id =?";
    private final String GET_COURSE_BY_ID = "SELECT * FROM course where id = ?";
	public void addCourse(Courses course) {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE))

		{
			preparedStatement.setString(1, course.getCourseName());
	
			preparedStatement.executeUpdate();
            
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of addCourse

		
		
	public int getIdOfCourseByName (Courses course) {
		try (Connection connection =ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_ID_OF_COURSE);){
			
		    preparedSt.setString(1,course.getCourseName());  
		    ResultSet rs = preparedSt.executeQuery();
		
		    
		    while(rs.next()) {
		  	course.setCourseId(rs.getInt("course_id"));
		    }
        return course.getCourseId();
        
		} catch (SQLException e) {
			System.out.println("error " + e);
			return 0;
		}
		
		
	}//end of getIdOfCourseByName
	

	public void deleteCourse(Integer courseId) {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt= connection.prepareStatement(DELETE_COURSE);) {
			preparedSt.setInt(1, courseId);

			int result = preparedSt.executeUpdate();
			System.out.println("Number of course deleted: " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of deleteCourse
	 
		
	public Courses getCourseById(Integer courseId) {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_COURSE_BY_ID);) {
			preparedSt.setInt(1, courseId);
			ResultSet rs = preparedSt.executeQuery();
			Courses course = new Courses();
			while (rs.next()) {
			
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setDesciption(rs.getString("course_description"));
				course.setDurationTime(rs.getString("course_duration_time"));
			//	course.setProfessorList(rs.getArray("professor_id"));
			}
			return course;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
		
}//end of CLASS











