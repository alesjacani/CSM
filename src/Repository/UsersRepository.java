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
	
	private final String ADD_COURSE =  "INSERT INTO course (course_name) VALUES (?)";
    private final String GET_ID_OF_COURSE= "SELECT course_id FROM course WHERE course_name=?";

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
	


		
		
}//end of CLASS











