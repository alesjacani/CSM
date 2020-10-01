package Service;

import java.util.List;

import Accounts.Grade;
import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Repository.ProfessorRepository;
import Repository.UsersRepository;
import Util.Messages;

import Exceptions.ProjectException;
public class ProfessorService {
	
static ProfessorRepository professorRepository = new ProfessorRepository();
static UsersRepository usersrRep= new UsersRepository();
public static void changePassword(String username,String password) {
	professorRepository.changeProfessorPassword(username, password);
}

	public static void addStudent(Student student,Courses course) {
		if(professorRepository.studentExists(student)) {
			throw new ProjectException(Messages.STUDENT_EXISTS.getMessage());
			}
		else {
			professorRepository.addStudent(student);
			professorRepository.getStudentByUsername(student);
			Courses c=usersrRep.getCourseByName(course.getCourseName());
			professorRepository.addStudentCourseByIDs(student.getIdStudent(), c.getCourseId());
			
			
		}
	}
	
	public static Student authenticateStudent(Student student) {
 		 if(professorRepository.studentExists(student)) {
 			professorRepository.getStudentByUsername(student);
 			 return student;
 		 }else {
 			 throw new ProjectException(Messages.WRONG_USERNAME_OR_PASSWORD.getMessage());
 		 }
 	 }
	
	
	
	public static void addStudentCoursebyIds(int studentid, int courseId) {
		professorRepository.addStudentCourseByIDs(studentid, courseId);
	}
	
	 public static Professor getProfByCourseName(String courseName) {
		  return professorRepository.getProfByCourseName(courseName);
	  }
	 
	 public static List<String> getCourseNameByProfessorUsername(String profUsername){
			    return  professorRepository.getCourseNameByProfessorUsername(profUsername);
		 }
	
	 public static void editCourseDetails(String description, String time, String courseName) {
		 professorRepository.editCourseDetails(description, time, courseName);
	 }
	 public static  boolean studentHasGrade(Courses course, Student student) {
		 return professorRepository.studentHasGrade(course, student);
	 }
	 
	 public static void gradeStudent (Courses course, Student student, Grade grade) {
		//Student s =AdminService.getStudentBySId(student.getIdStudent());
		//Student st = AdminService.getAllStudentByUsername(s.getUserNameStudent());
		 if (professorRepository.studentExistsBySId(student)) {
			 professorRepository.addGrade(course, student , grade);
		 }else {
			 throw new ProjectException(Messages.STUDENT_DOES_NOT_EXISTS.getMessage());
		 }
	 }
	 
	 
	// public boolean studentBelongToCourse(Student student) {
	//	 if(professorRepository.studentExistsBySId(student)) {
			 
	//	 }
	// }
	 public static int getGradeByStudentId(int studentId) {
		 return professorRepository.getGradeByStudentId(studentId);
	 }
	 
	 public static int getGradeByStudentCourseId(int studentId,int courseId) {
		 return professorRepository.getGradeByStudentCourseId(studentId, courseId);
	 }
	 
	 
	 public static List<Student> studentWithMaxGrade(int courseId) {
		return professorRepository.studentWithMaxGradeByCourseId(courseId);
	 }
}//end of CLASs
