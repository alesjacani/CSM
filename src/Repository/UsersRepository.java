package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Util.ConnectionDb;

public class UsersRepository {
	
	//*ADD, getIdbyName, DELETE for COURSE* // metodat e ADMINIT
	private final String ADD_COURSE =  "INSERT INTO course (course_name) VALUES (?)";
    private final String GET_ID_OF_COURSE= "SELECT course_id FROM course WHERE course_name=?";
    private final String DELETE_ALL_COURSE = "DELETE FROM course";
    private final String DELETE_RECORDS_GRADE="DELETE FROM grade";
    		
    private final String GET_COURSE_BY_NAME = "SELECT * FROM course where course_name = ?";
    private final String GET_COURSE_BY_PROFESSOR_USERNAME = "SELECT * FROM course \r\n" + 
    		"FULL JOIN professor ON course.professor_id=professor.professor_id\r\n" + 
    		"WHERE professor_username=?";
    private final String COURSE_EXIST = "SELECT COUNT (*) FROM course WHERE course_name=?";
    ////////////////////////////
    private final String DELETE_COURSE_BY_NAME = "DELETE FROM course WHERE course_name=?";
    private final String DELETE_COURSE_FROM_GRADE="DELETE FROM grade WHERE course_id=?";
    ///////////////////////EDDHE NGA TABELA GRADE
    private final String LIST_ALL_COURSE = "SELECT * FROM course  \r\n" + 
    		"LEFT JOIN professor ON course.professor_id=professor.professor_id";
    
    private final String ADD_PROFESSOR="INSERT INTO professor (professor_name, professor_surname,professor_username,professor_password) VALUES (?,?,?,?)";
    private final String GET_ID_OF_PROFESSOR= "SELECT professor_id FROM professor WHERE professor_username=?";
    private final String ADD_PROF_INTO_A_COURSE= "UPDATE course SET professor_id=? WHERE course_id=?";
    
   
    private final String PROFESSOR_EXIST="SELECT COUNT (*) FROM  professor WHERE professor_username=? AND professor_password=?";
    private final String PROFESSOR_EXIST2="SELECT COUNT (*) FROM  professor WHERE professor_username=?";
    private final String GET_PROF_BY_USERNAME="SELECT * FROM professor WHERE professor_username=?";
    //private final String GET_COURSE_NAME_BY_PROFESSOR_USERNAME="SELECT course.course_name FROM course INNER JOIN professor ON course.professor_id= professor.professor_id WHERE professor.professor_username=?";
    private final String LIST_ALL_PROFESSORS_BY_USERNAME= "SELECT * FROM professor WHERE professor_username=? ";
    private final String LIST_ALL_STUDENT_BY_USERNAME="SELECT * FROM student WHERE student_username=?";
    
    private final String GET_STUDENT_BY_SID ="SELECT student_id,student_name,student_surname,student_username FROM student WHERE student_id=?";
    private final String LIST_ALL_PROFESSORS="SELECT * FROM professor";
    
   private final String COURSE_HAS_PROF="SELECT COUNT (professor_id) FROM course WHERE course_name=?";
   private final String EDIT_PROFESSOR_DETAILS="UPDATE professor SET professor_name=?,professor_surname=?,professor_username=? WHERE professor_id=?";
   private final String DELETE_PROFESSOR_BY_USERNAME="DELETE FROM professor WHERE professor_username=?";
   private final String DELETE_PROFESSOR_FROM_COURSE="UPDATE course SET professor_id=null WHERE professor_id=?";

   
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
	
	
public boolean courseHasProfessor(Courses course) {
		
		try(Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COURSE_HAS_PROF)) {
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

  
  

	
	
	

	public void deleteCourse() {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt= connection.prepareStatement(DELETE_ALL_COURSE);) {
			int result = preparedSt.executeUpdate();
			System.out.println("Number of course deleted: " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of deleteCourse 
	
	public void deleteRecordsGrade() {
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt= connection.prepareStatement(DELETE_RECORDS_GRADE);) {
			preparedSt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}//end of deleteC
	

	 public void deleteCourseByName(String courseName) {
		 try (Connection connection = ConnectionDb.getConnection();
			  PreparedStatement preparedSt= connection.prepareStatement(DELETE_COURSE_BY_NAME);) {
			    preparedSt.setString(1, courseName);
				preparedSt.executeUpdate();
			}
		 catch (SQLException e) {
				System.out.println("error " + e);
			}
	 }//end of delete by n
		
	public Courses getCourseByName(String courseName) {
	
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_COURSE_BY_NAME);) {
			
			preparedSt.setString(1, courseName);
			ResultSet rs = preparedSt.executeQuery();
			Courses course = new Courses();
			while (rs.next()) {
			
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setDesciption(rs.getString("course_description"));
				course.setDurationTime(rs.getString("course_duration_time"));
			    
				//course.setProfessorList(rs.getInt("professor_id"));
			}
			return course;
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}//end of getCourseById
	
	public List<Courses> getCourseByProfessorUsername(String professorUsername) {
	List <Courses> courses = new ArrayList();
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(GET_COURSE_BY_PROFESSOR_USERNAME);) {
			
			preparedSt.setString(1, professorUsername);
			ResultSet rs = preparedSt.executeQuery();
			
			while (rs.next()) {
			    Courses c = new Courses ();
		        Professor p = new Professor ();
		        listAllProfessorByUsername(p.getUsernameProf());
		        
		        p.setUsernameProf(rs.getString("professor_username"));
		    	
					
				listAllProfessorByUsername(p.getUsernameProf()).add(p);
				
		        
		    	c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setDesciption(rs.getString("course_description"));
				c.setDurationTime(rs.getString("course_duration_time"));
				c.setProfessorId(rs.getInt("professor_id"));
		
				
				c.setProfessorList(listAllProfessorByUsername(p.getUsernameProf()));
				courses.add(c);
				
			
			}
		    return courses;
			
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}//end of getCourseByPROFESSOR-username
	
	public List<Courses> listAllCourses() {
		List <Courses> courses = new ArrayList<>();
		
		try (Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_COURSE);){
			    ResultSet rs = preparedSt.executeQuery();
			    
			    
  			    while (rs.next()) {
			        Courses c = new Courses ();
			        Professor p = new Professor ();
			        
			       
			        p.setIdProfessor(rs.getInt("professor_id"));
			    	p.setFirstNameProf(rs.getString("professor_name"));
				    p.setLastNameProf(rs.getString("professor_surname"));
					p.setUsernameProf(rs.getString("professor_username"));
					p.setPasswordProf(rs.getString("professor_password"));
					listAllProfessorByUsername(p.getUsernameProf()).add(p);
					
				
			        
			    	c.setCourseId(rs.getInt("course_id"));
					c.setCourseName(rs.getString("course_name"));
					c.setDesciption(rs.getString("course_description"));
					c.setDurationTime(rs.getString("course_duration_time"));
					c.setProfessorList(listAllProfessorByUsername(p.getUsernameProf()));
			       
					
					
					courses.add(c);
					
				
				}
			    return courses;
	
		} catch (SQLException exception) {
			System.out.println("error " + exception);
			return null;
		}
		
	}//end of listAllCourses
	
	
	public List<Professor> listAllProfessorByUsername(String professorUsername){	
	
	List <Professor> professors= new ArrayList();
	try (Connection connection = ConnectionDb.getConnection();
			 
			PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_PROFESSORS_BY_USERNAME);){
		    preparedSt.setString(1, professorUsername);
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
	
	
	public Student getAllStudentByUsername(String username){	
		
		
		try (Connection connection = ConnectionDb.getConnection();
				 
				PreparedStatement preparedSt = connection.prepareStatement(LIST_ALL_STUDENT_BY_USERNAME);){
			    preparedSt.setString(1, username);
			    ResultSet rs = preparedSt.executeQuery();
			    Student s = new Student();

			    
				    while (rs.next()) {
			       s.setIdStudent(rs.getInt("student_id"));
			       s.setFirstNameStudent(rs.getString("student_name"));
			       s.setLastNameStudent(rs.getString("student_surname"));
			       s.setUserNameStudent(rs.getString("student_username"));
			       s.setPasswordStudent(rs.getString("student_password"));
			        		        
			    
				
				}
			    return s;

		} catch (SQLException exception) {
			System.out.println("error " + exception);
			return null;
		}
			
		}//end of listAllProfessorById
	
	

public Student listAllStudentBySId(int id){	

		try (Connection connection = ConnectionDb.getConnection();
				 
				PreparedStatement preparedSt = connection.prepareStatement(GET_STUDENT_BY_SID);){
			    preparedSt.setInt(1, id);
			    ResultSet rs = preparedSt.executeQuery();
			    Student s = new Student();

			    
				    while (rs.next()) {
			       s.setIdStudent(rs.getInt("student_id"));
			       s.setFirstNameStudent(rs.getString("student_name"));
			       s.setLastNameStudent(rs.getString("student_surname"));
			       s.setUserNameStudent(rs.getString("student_username"));
			       
			       
			  
				
				}
			    return s;

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
			preparedStatement.setString(2, professor.getPasswordProf());
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
	
	
	
	public boolean professorExistsOnlyUsername(Professor professor) {
		try(Connection connection = ConnectionDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(PROFESSOR_EXIST2);) {
			
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
		
	
public void editProfessorDetails(Professor professor) {
	try( Connection connection =ConnectionDb.getConnection();
			PreparedStatement preparedSt = connection.prepareStatement(EDIT_PROFESSOR_DETAILS);) {
		
		preparedSt.setString(1, professor.getFirstNameProf() );
		preparedSt.setString(2, professor.getLastNameProf()) ;
		preparedSt.setString(3, professor.getUsernameProf() );
		preparedSt.setInt(4, professor.getIdProfessor());
		preparedSt.executeUpdate();
	}catch (SQLException e) {
		System.out.println("error " + e);
		
	}
}
	
	
	public Professor getProfessorByUsername(Professor professor) {
	//	List <Professor> professors = new ArrayList();
			try (Connection connection = ConnectionDb.getConnection();
					PreparedStatement preparedSt = connection.prepareStatement(GET_PROF_BY_USERNAME);) {
				
				preparedSt.setString(1, professor.getUsernameProf());
			
				ResultSet rs = preparedSt.executeQuery();
			
				while (rs.next()) {
				    professor.setIdProfessor(rs.getInt("professor_id"));
					professor.setFirstNameProf(rs.getString("professor_name"));
					professor.setLastNameProf(rs.getString("professor_surname"));
					professor.setUsernameProf(rs.getString("professor_username"));
					professor.setPasswordProf(rs.getString("professor_password"));
				}
				return professor;
				
			} catch (SQLException e) {
				System.out.println("error " + e);
				return null;
			}
		}//end of getProfessorByUsernameAndPassword
	
	
	
	
	
	 public void deleteProfessorByUsername(Professor professor) {
		 try (Connection connection = ConnectionDb.getConnection();
			  PreparedStatement preparedSt= connection.prepareStatement(DELETE_PROFESSOR_BY_USERNAME);) {
			    preparedSt.setString(1, professor.getUsernameProf());
				preparedSt.executeUpdate();
			}
		 catch (SQLException e) {
				System.out.println("error " + e);
			}
	 }//end of delete by n

	 
	 
	 
	 
	 
	 public void deleteProfessorFromCourse(int  id) {
		 try (Connection connection = ConnectionDb.getConnection();
			  PreparedStatement preparedSt= connection.prepareStatement(DELETE_PROFESSOR_FROM_COURSE);) {
			    preparedSt.setInt(1, id);
				preparedSt.executeUpdate();
			}
		 catch (SQLException e) {
				System.out.println("error " + e);
			}
	 }//end of delete by n

	 
	 
	 public void deleteCourseFromGrade (int id) {
		 try (Connection connection = ConnectionDb.getConnection();
				  PreparedStatement preparedSt= connection.prepareStatement(DELETE_COURSE_FROM_GRADE);){
			 preparedSt.setInt(1, id);
			 preparedSt.executeUpdate();
			
		}  catch (SQLException e) {
			System.out.println("error " + e);
		}
	 }
}//end of CLASS











