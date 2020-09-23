package Service;

import java.util.List;

import Course.Courses;
import Exceptions.ProjectException;
import Repository.UsersRepository;
import Util.Messages;



public class CourseService {
	
      static UsersRepository usersRepository = new UsersRepository();
      
      public static void addCourse(Courses course) {
    	  if (usersRepository.courseExists(course)) {
    		     throw new ProjectException(Messages.COURSE_EXITS.getMessage());
		} else {
        
         usersRepository.addCourse(course);
		}

  	}
      
    
      public static int getCourseIdyName (Courses course) {
    	  
    		  return usersRepository.getIdOfCourseByName(course) ;
	 }
      
      
      
      public static List<Courses> getCourseByID (Integer courseId) {
    	    return usersRepository.getCourseById(courseId);
	
      }  
      
      
      
      
  	public static void deleteCourse(Courses course) {
		usersRepository.deleteCourse(course);
  	}
      
  	public static List<Courses> listAllCourses(){
  		return usersRepository.listAllCourses();
  	}
  	
}
