package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Accounts.Professor;
import Course.Courses;
import Util.ConnectionDb;

public class UsersRepository {
	
	
	//*ADD, getIdbyName, DELETE for COURSE* // metodat e ADMINIT
	private final String ADD_COURSE =  "INSERT INTO course (course_name) VALUES (?)";
    private final String GET_ID_OF_COURSE= "SELECT course_id FROM course WHERE course_name=?";
    private final String DELETE_ALL_COURSE = "DELETE FROM course";
    private final String GET_COURSE_BY_ID = "SELECT * FROM course where course_id = ?";
    private final String GET_COURSE_BY_PROFESSOR_ID = "SELECT * FROM course where professor_id = ?";
    private final String COURSE_EXIST = "SELECT COUNT (*) FROM course WHERE course_name=?";
    private final String DELETE_COURSE_BY_ID = "DELETE FROM course WHERE course_id=?";
    private final String LIST_ALL_COURSE = "SELECT * FROM course INNER JOIN professor ON course.professor_id=professor.professor_id";
    
    private final String ADD_PROFESSOR="INSERT INTO professor (professor_name, professor_surname,professor_username,professor_password) VALUES (?,?,?,?)";
    private final String GET_ID_OF_PROFESSOR= "SELECT professor_id FROM professor WHERE professor_username=?";
    private final String ADD_PROF_INTO_A_COURSE= "UPDATE course SET professor_id=? WHERE course_id=?";
    
   
    private final String PROFESSOR_EXIST="SELECT COUNT (*) FROM  professor WHERE professor_username=?";
    private final String GET_PROF_BY_USERNAME_AND_PASSWORD="SELECT * FROM professor WHERE professor_username=? AND professor_password=?";
   // private final String GET_COURSE_NAME_BY_PROFESSOR_USERNAME="SELECT course.course_name FROM course INNER JOIN professor ON course.professor_id= professor.professor_id WHERE professor.professor_username=?";
    private final String LIST_ALL_PROFESSORS_BY_ID= "SELECT * FROM professor WHERE professor_id=?";
    private final String LIST_ALL_PROFESSORS="SELECT * FROM professor";
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
		
		try(Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COURSE_EXIST)) {
			preparedStatement.setString(1, course.getCourseName());
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
	

	 public void deleteCourseById(Integer courseId) {
		 try (Connection connection = ConnectionDb.getConnection();
			  PreparedStatement preparedSt= connection.prepareStatement(DELETE_COURSE_BY_ID);) {
			    preparedSt.setInt(1, courseId);
				preparedSt.executeUpdate();
			}
		 catch (SQLException e) {
				System.out.println("error " + e);
			}
	 }//end of delete by id
		
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
				//course.setProfessorList(rs.getInt("professor_id"));
			}
			return courses;
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}//end of getCourseById
	
	public List<Courses> getCourseByProfessorId(Integer id) {
	List <Courses> courses = new ArrayList();
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_COURSE_BY_PROFESSOR_ID);) {
			
			preparedSt.setInt(1, id);
			ResultSet rs = preparedSt.executeQuery();
			Courses course = new Courses();
			while (rs.next()) {
			    Courses c = new Courses ();
		        Professor p = new Professor ();
		        listAllProfessorById(p.getIdProfessor());
		        
		        p.setIdProfessor(rs.getInt("professor_id"));
		    	
					
				listAllProfessorById(p.getIdProfessor()).add(p);
				
		        
		    	c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setDesciption(rs.getString("course_description"));
				c.setDurationTime(rs.getString("course_duration_time"));
				c.setProfessorId(rs.getInt("professor_id"));
		
				
				c.setProfessorList(listAllProfessorById(p.getIdProfessor()));
				courses.add(c);
				
			
			}
		    return courses;
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}//end of getCourseByPROFESSOR-ID
	
	public List<Courses> listAllCourses() {
		List <Courses> courses = new ArrayList<>();
		
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_COURSE);){
			    ResultSet rs = preparedSt.executeQuery();
			    
			    
  			    while (rs.next()) {
			        Courses c = new Courses ();
			        Professor p = new Professor ();
			        listAllProfessorById(p.getIdProfessor());
			        
			        p.setIdProfessor(rs.getInt("professor_id"));
			    	p.setFirstNameProf(rs.getString("professor_name"));
				    p.setLastNameProf(rs.getString("professor_surname"));
					p.setUsernameProf(rs.getString("professor_username"));
					p.setPasswordProf(rs.getString("professor_password"));
						
					listAllProfessorById(p.getIdProfessor()).add(p);
					
			        
			    	c.setCourseId(rs.getInt("course_id"));
					c.setCourseName(rs.getString("course_name"));
					c.setDesciption(rs.getString("course_description"));
					c.setDurationTime(rs.getString("course_duration_time"));
					c.setProfessorId(rs.getInt("professor_id"));
			
					
					c.setProfessorList(listAllProfessorById(p.getIdProfessor()));
					courses.add(c);
					
				
				}
			    return courses;
	
		} catch (SQLException exception) {
			System.out.println("error " + exception);
			return null;
		}
		
	}//end of listAllCourses
	
	
	public List<Professor> listAllProfessorById(Integer professorId){	
	
	List <Professor> professors= new ArrayList();
	try (Connection connection = ConnectionDb.getConnection();
			 
			PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_PROFESSORS_BY_ID);){
		    preparedSt.setInt(1, professorId);
		    ResultSet rs = preparedSt.executeQuery();
		    Professor p = new Professor ();

		    
			    while (rs.next()) {
		       
		        		        
		        p.setIdProfessor(rs.getInt("professor_id"));
		    	p.setFirstNameProf(rs.getString("professor_name"));
			    p.setLastNameProf(rs.getString("professor_surname"));
				p.setUsernameProf(rs.getString("professor_username"));
				p.setPasswordProf(rs.getString("professor_password"));
					
				professors.add(p);
			
			}
		    return professors;

	} catch (SQLException exception) {
		System.out.println("error " + exception);
		return null;
	}
		
	}//end of listAllProfessorById
	
	public List<Professor> listAllProfessors() {
		List <Professor> professors = new ArrayList<>();
		
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_PROFESSORS);){
			    ResultSet rs = preparedSt.executeQuery();
			    
			    
  			    while (rs.next()) {
			        Professor p = new Professor ();
			       
			        
			        p.setIdProfessor(rs.getInt("professor_id"));
			    	p.setFirstNameProf(rs.getString("professor_name"));
				    p.setLastNameProf(rs.getString("professor_surname"));
					p.setUsernameProf(rs.getString("professor_username"));
					p.setPasswordProf(rs.getString("professor_password"));
						
				    professors.add(p);
					
				
				}
			    return professors;
	
		} catch (SQLException exception) {
			System.out.println("error " + exception);
			return null;
		}
		
	}
	
	
	public void addProfessor(Professor professor) {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedst = connection.prepareStatement(ADD_PROFESSOR);){
			preparedst.setString(1, professor.getFirstNameProf());
			preparedst.setString(2, professor.getLastNameProf());
			preparedst.setString(3, professor.getUsernameProf());
			preparedst.setString(4, professor.getPasswordProf());
			
			preparedst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of addProfessor
	
	public boolean professorExists(Professor professor) {
		try(Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(PROFESSOR_EXIST);) {
			preparedStatement.setString(1, professor.getUsernameProf());
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
	}
	
	public int getProfessorIdByUsername (Professor professor) {
		try (Connection connection =ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_ID_OF_PROFESSOR);){
			
		    preparedSt.setString(1,professor.getUsernameProf());  
		    ResultSet rs = preparedSt.executeQuery();
		
		    
		    while(rs.next()) {
		  	professor.setIdProfessor(rs.getInt("professor_id"));
		    }
        return professor.getIdProfessor();
        
		} catch (SQLException e) {
			System.out.println("error " + e);
			return 0;
		}
		
		
	}
	
	public void addProfIntoCourse(Professor professor, Courses course) {
		try ( Connection connection =ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(ADD_PROF_INTO_A_COURSE);) {
			preparedSt.setInt(1, professor.getIdProfessor());
			preparedSt.setInt(2, course.getCourseId());
			preparedSt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			
		}
	}//end of addProfIntoCourse
		
	
	
	public List<Professor> getProfessorByUsernameAndPassword(Professor professor) {
		List <Professor> professors = new ArrayList();
			try (Connection connection = ConnectionDb.getConnection();
					PreparedStatement preparedSt = connection.prepareStatement(GET_PROF_BY_USERNAME_AND_PASSWORD);) {
				
				preparedSt.setString(1, professor.getUsernameProf());
				preparedSt.setString(2, professor.getPasswordProf());
				ResultSet rs = preparedSt.executeQuery();
			
				while (rs.next()) {
				    professor.setIdProfessor(rs.getInt("professor_id"));
					professor.setFirstNameProf(rs.getString("professor_name"));
					professor.setLastNameProf(rs.getString("professor_surname"));
					professor.setUsernameProf(rs.getString("professor_username"));
					professor.setPasswordProf(rs.getString("professor_password"));
					
					professors.add(professor);
				
				}
				return professors;
				
			} catch (SQLException e) {
				System.out.println("error " + e);
				return null;
			}
		}//end of getProfessorByUsernameAndPassword
	
	/*public void getCourseNameByProfessorUsername( Courses course,Professor professor){
    List<Courses> courses = new ArrayList();
    List <Professor> professors = new ArrayList();
		try(Connection connection = ConnectionDb.getConnection();
			PreparedStatement preparedst = connection.prepareStatement(GET_COURSE_NAME_BY_PROFESSOR_USERNAME); ) {
			
			preparedst.setString(1, professor.getUsernameProf());
		    
			ResultSet rs = preparedst.executeQuery();
			while(rs.next()) {
			
				course.setCourseName(rs.getString("course_name"));
				course.setProfessorList(professors);
				//professor.setCourseList(courses);
		}
			System.out.println(course.getCourseName());
			
		
		

		} catch (SQLException e) {
			System.out.println("error " + e);
		
		}
	}*/
	
	
}//end of CLASS











