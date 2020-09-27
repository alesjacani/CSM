package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Accounts.Professor;
import Course.Courses;
import Exceptions.ProjectException;
import Repository.UsersRepository;
import Util.ConnectionDb;
import Util.Messages;



public class AdminService {
	
      static UsersRepository usersRepository = new UsersRepository();
       
       public static void addCourse(Courses course) {
  if (usersRepository.courseExists(course)) {
	  throw new ProjectException(Messages.COURSE_EXITS.getMessage());
  }else {usersRepository.addCourse(course);}
        
    		}
      
       public static int getCourseIdyName (Courses course) {
    	  return usersRepository.getIdOfCourseByName(course) ;
	    }
      
       public static List<Courses> getCourseByID (Integer courseId) {
    	    return usersRepository.getCourseById(courseId);
	    }  
       
       public static List<Courses> getCourseByProfessorId (Integer id){
    	   return usersRepository.getCourseByProfessorId(id);
       }
      
       public static void deleteCourse(Courses course) {
		usersRepository.deleteCourse(course);
  	    }
       
   	   public static void deleteCourseById(Integer courseId) {
  		usersRepository.deleteCourseById(courseId);
     	}
      
  	   public static List<Courses> listAllCourses(){
  		return usersRepository.listAllCourses();
  	    }
  	
  	    public static List<Professor> listAllProfessorById(Integer id){
       return usersRepository.listAllProfessorById(id);
  	    }
  	    
  	    public static List<Professor> listAllProfessors(){
  	    	return usersRepository.listAllProfessors();
  	    }
  	 public static void addProfessor(Professor professor) {
  	   if (usersRepository.professorExists(professor)) {
  		throw new ProjectException(Messages.PROFESSOR_EXISTS.getMessage());
  	} else {
           usersRepository.addProfessor(professor);
  	} 
    }
  	 
  	 public static int getProfIdbyUsername(Professor professor) {
  		 return usersRepository.getProfessorIdByUsername(professor);
  	 }
  	   
  	 public static void addProfIntoCourse(Professor professor, Courses course) {
  		 usersRepository.addProfIntoCourse(professor, course);
  	 }
  	  
  	 public static Professor authenticateProfessor(Professor professor) {
  		 if(usersRepository.professorExists(professor)) {
  			usersRepository.getProfessorByUsernameAndPassword(professor);
  			 return professor;
  		 }else {
  			 throw new ProjectException(Messages.WRONG_USERNAME_OR_PASSWORD.getMessage());
  		 }
  	 }
  	 
  
  	/*public static void getCourseNameByProfessorUsername(Courses course, Professor professor){
  		
	usersRepository.getCourseNameByProfessorUsername(course,professor);
	}*/
}//end of CLASS COURSESERVICE
