package Service;

import Accounts.Professor;
import Course.Courses;
import Repository.UsersRepository;
import Util.Messages;

import Exceptions.ProjectException;
public class ProfessorService {
	
	
static UsersRepository profRepository  = new UsersRepository();


	public static void addProfessors(Professor professor,Courses course) {
		if(profRepository.professorExists(professor,course)) {
			throw new ProjectException(Messages.PROFESSOR_EXISTS.getMessage());
			
		} else {
            profRepository.addProfessor(professor, course);
		}
	

	}//end of addProfessors
	
	

	
	
}//end of CLASs
