package Repository;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;

public class UsersRepository {
	
	
	// ekzekutohen queryt ne db 
		public void addProfessor(Professor p, Courses c) {
		c.getProfessorList().add(p);
		
		}
		
		public boolean professorExists(Professor p, Courses c) {
			 for (Professor prof : c.getProfessorList()) {
					if (prof.equals(p)) {
						return true;
					}
				}
				return false;
			}
		
		
		
		
		
		// ekzekutohen queryt ne db 
		public void addStudent(Student student) {
			// shto studentin ne databaze 
		}
		
		public boolean studentExists(Student student) {
			// query qe kthen true nese studenti ekziston ne databaze
			return true;
		}

		
		
	
		
		
}//end of CLASS











