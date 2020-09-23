package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Course.Courses;
import Util.ConnectionDb;






public class UsersRepository {
	
	//*ADD, getIdbyName, DELETE for COURSE*
	private final String ADD_COURSE =  "INSERT INTO course (course_name) VALUES (?)";
    private final String GET_ID_OF_COURSE= "SELECT course_id FROM course WHERE course_name=?";
    private final String DELETE_ALL_COURSE = "DELETE FROM course";
    private final String GET_COURSE_BY_ID = "SELECT * FROM course where course_id = ?";
    
    private final String DELETE_COURSE_BY_ID = "DELETE FROM course WHERE course_id=?";
    private final String LIST_ALL_COURSE = "SELECT * FROM course";

    
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

	
	
  public boolean courseExists(Courses course) {
	  try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE)){
			preparedStatement.executeUpdate();
            return true;
		}
	  catch (SQLException e) {
			System.out.println("error " + e);
		}
	  return false;
      }
		
  
  
  
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
	

	public void deleteCourse(Courses course) {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt= connection.prepareStatement(DELETE_ALL_COURSE);) {
			int result = preparedSt.executeUpdate();
			System.out.println("Number of course deleted: " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of deleteCourse 
	

	 
		
	public List<Courses> getCourseById(Integer courseId) {
	List <Courses> courses = new ArrayList();
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
			    courses.add(course);
			//	course.setProfessorList(rs.getArray("professor_id"));
			}
			return courses;
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}//end of getCourseById
	
	public List<Courses> listAllCourses() {
		List <Courses> courses = new ArrayList<>();
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_COURSE);){
			    ResultSet rs = preparedSt.executeQuery();
			           
			    while (rs.next()) {
			    	Courses course = new Courses(); 
			    	course.setCourseId(rs.getInt("course_id"));
					course.setCourseName(rs.getString("course_name"));
					course.setDesciption(rs.getString("course_description"));
					course.setDurationTime(rs.getString("course_duration_time"));
				    courses.add(course);
					
				}
			    return courses;
		} catch (SQLException exception) {
			System.out.println("error " + exception);
			return null;
		}
		
	}//end of listAllCourses
		
}//end of CLASS











