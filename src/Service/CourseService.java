package Service;

import Course.Courses;
import Repository.UsersRepository;


public class CourseService {
	
      static UsersRepository usersRepository = new UsersRepository();
      
      public static void addCourse(Courses course) {
  		usersRepository.addCourse(course);
  	}
      
      
      public static int getCourseIdyName (Courses course) {
    	  
    	return usersRepository.getIdOfCourseByName(course) ;
    	 
      }
      
      
      
}
