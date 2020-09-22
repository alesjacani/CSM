package Service;

import Course.Courses;
import Exceptions.ProjectException;
import Repository.UsersRepository;
import Util.Messages;



public class CourseService {
	
      static UsersRepository usersRepository = new UsersRepository();
      
      public static void addCourse(Courses course) {
  		usersRepository.addCourse(course);
  	}
      
      
      public static int getCourseIdyName (Courses course) {
    	  
    	return usersRepository.getIdOfCourseByName(course) ;
    	 
      }
      
  	public static void deleteCourse(Courses course) {
		if (usersRepository.getCourseById(course.getCourseId()) != null) {
			usersRepository.deleteCourse(course.getCourseId());
		} else {
			throw new ProjectException(Messages.COURSE_DOES_NOT_EXISTS.getMessage());
		}
	}
      
  	
  	
}
