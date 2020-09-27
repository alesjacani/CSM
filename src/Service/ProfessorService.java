package Service;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Repository.ProfessorRepository;
import Repository.UsersRepository;
import Util.Messages;

import Exceptions.ProjectException;
public class ProfessorService {
	
static ProfessorRepository professorRepository = new ProfessorRepository();

public static void changePassword(String username,String password) {
	professorRepository.changeProfessorPassword(username, password);
}

	public static void addStudent(Student student) {
		if(professorRepository.studentExists(student)) {
			professorRepository.addStudent(student);

		}
		else {
			throw new ProjectException(Messages.STUDENT_EXISTS.getMessage());
		}
	}
	
	public static int getIdStudentByUsername (Student s) {
		return professorRepository.getStudentIdByUsername(s);
	}
	
	public static void addStudentCoursebyIds(int studentid, int courseId) {
		professorRepository.addStudentCourseByIDs(studentid, courseId);
	}
}//end of CLASs
