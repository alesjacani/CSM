package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.translation.messages_bg;

import Accounts.Professor;
import Accounts.Student;
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
  }else {
	  usersRepository.addCourse(course);}
      usersRepository.getCourseByName(course.getCourseName());
    		}
      
       
       public static Courses authenticateCourse(Courses course) {
    		 if(usersRepository.courseExists(course)) {
    			return usersRepository.getCourseByName(course.getCourseName());
    			
    		 }else {
    			 throw new ProjectException(Messages.COURSE_DOES_NOT_EXISTS.getMessage());
    		 }
    	 }
       
       
      
      
       public static Courses getCourseByName (String courseName) {
    	    return usersRepository.getCourseByName(courseName);
	    }  
       
    
       public static List<Courses> getCourseByProfessorUsername(String professorUsername){
    	   return usersRepository.getCourseByProfessorUsername(professorUsername);
       }
      
       public static void deleteCourse() {
		usersRepository.deleteCourse();
		usersRepository.deleteRecordsGrade();
  	    }
       
   	   public static void deleteCourseByName(Courses course) {
   		 if(usersRepository.courseExists(course)) {
   			  Courses c=usersRepository.getCourseByName(course.getCourseName());
   			  usersRepository.deleteCourseByName(course.getCourseName());
   			  
   			  usersRepository.deleteCourseFromGrade(c.getCourseId());
   		  }else {
   			  throw new ProjectException(Messages.COURSE_DOES_NOT_EXISTS.getMessage());
   		  }
     	}
      
  	   public static List<Courses> listAllCourses(){
  		return usersRepository.listAllCourses();
  	    }
  	
  	  //  public static List<Professor> listAllProfessorById(Integer id){
      // usersRepository.listAllProfessorByUsername(id);
  	  // }
  	    
  	    public static List<Professor> listAllProfessors(){
  	    	return usersRepository.listAllProfessors();
  	    }
  	    
  	    public static Student getStudentBySId(int studentId){
  	    	return usersRepository.getAllStudentBySId(studentId);
  	    	}
  	    
  	    public static Student getAllStudentByUsername (String username) {
  	    	return usersRepository.getAllStudentByUsername(username);
  	    }
  	    
  	    public static List<Student> listStudentByCourseName(String courseName){
  	    	return usersRepository.listStudentByCourseName(courseName);
  	    }
  	    
  	//   public boolean  
  	   
  	    //shtimi i te dhenave te proff ne professor table
  	    //shtimi i prof ne tabelen e kursit
  	    //marrja e id se profesorit te shtuar
  	    
  	 public static void addProfessor(Professor professor,Courses course) {
  		 
  	 if (usersRepository.professorExistsOnlyUsername(professor)) {
  	     throw new ProjectException(Messages.PROFESSOR_EXISTS.getMessage());
  	  }  
  	 else {
  	    	     //   if(usersRepository.courseHasProfessor(course)) {
  	    		//         throw new ProjectException(Messages.COURSE_HAS_PROFESSOR.getMessage());
  	    	     //      }else {
                        usersRepository.addProfessor(professor);
                        usersRepository.getProfessorIdByUsername(professor);
                        Courses c =usersRepository.getCourseByName(course.getCourseName());
                        usersRepository.addProfIntoCourse(professor, c);
                     //         }
              } 
    }
  	 
  	 public static void courseHasProf(Courses course) {
  		if( usersRepository.courseHasProfessor(course)) {
  		throw new ProjectException(Messages.COURSE_HAS_PROFESSOR.getMessage());
  		}
  		
  	 }
  
  	 
  	 public static Professor authenticateProfessor(Professor professor) {
  		 if(usersRepository.professorExists(professor)) {
  			usersRepository.getProfessorByUsername(professor);
  			 return professor;
  		 }else {
  			 throw new ProjectException(Messages.WRONG_USERNAME_OR_PASSWORD.getMessage());
  		 }
  	 }
 ////////
  	 public static Professor authenticateProfessorByUsername(Professor professor) {
  		 if(usersRepository.professorExistsOnlyUsername(professor)) {
  			 usersRepository.getProfessorByUsername(professor);
  			 return professor;
  		 }
  		 else {
  			 throw new ProjectException(Messages.PROFESSOR_DOES_NOT_EXISTS.getMessage());
  		 }
  	 }
  //////	 
	 public static void addProfIntoCourse(Professor professor, Courses course) {
		 if(usersRepository.courseHasProfessor(course)) {
	    		    throw new ProjectException(Messages.COURSE_HAS_PROFESSOR.getMessage());
	    	        }else {
  		    usersRepository.addProfIntoCourse(professor, course);
  		 }
  	 }
  	 
  	 
  	 public static int getProfIdbyUsername(Professor professor) {
  		 return usersRepository.getProfessorIdByUsername(professor);
  	 }
  	   
  	
  	 public static void editProfessorDetails(Professor professor) {
  		 if(usersRepository.professorExistsOnlyUsername(professor)) {
  			 throw new ProjectException("TRY ANOTHER USERNAME!");
  		 }else {
  		 usersRepository.editProfessorDetails(professor);
  	 }}
  	  
  	
  	 /////////////////////////////////////////////////////////////
  	 public static void deleteProfessorByUsername(Professor professor){
  		if(usersRepository.professorExistsOnlyUsername(professor)) {
  			int id= usersRepository.getProfessorIdByUsername(professor);
  			usersRepository.deleteProfessorFromCourse(id);
  			 usersRepository.deleteProfessorByUsername(professor);
  			 
  			 
  		}else {
  			throw new ProjectException(Messages.PROFESSOR_DOES_NOT_EXISTS.getMessage());
  		}
  		 
  			// usersRepository.deleteProfessorFromCourse(p);
  		
  	 }
  	 ///////////////////////////////////////////////////////////////////
  	 
  	 
  	public static List<Courses> listCourseByStudentUsername(String studentUsername){
  		return usersRepository.listCourseBySUsername(studentUsername);
  				
  	}
 
}//end of CLASS COURSESERVICE
