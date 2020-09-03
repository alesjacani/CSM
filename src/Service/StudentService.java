package Service;


import Accounts.Student;
import Repository.UsersRepository;
import Util.Messages;

import Exceptions.ProjectException;

public class StudentService {
	
	
	
	
	
	static UsersRepository studentRepository  = new UsersRepository();


	public static void addStudents(Student student) {
		if(studentRepository.studentExists(student)) {
			throw new ProjectException(Messages.STUDENT_EXISTS.getMessage());
		} else {
			studentRepository.addStudent(student);;
		}
	}

}
