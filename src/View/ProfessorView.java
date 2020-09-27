package View;

import java.util.InputMismatchException;
import java.util.Scanner;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Exceptions.ProjectException;
import Service.AdminService;
import Service.ProfessorService;

public class ProfessorView {
	public void professorMenu(Professor professor) {
		System.out.println("\n");

		System.out.printf("<<<<<<<<<< WELCOME TO PROFESSOR MENU %S %S  >>>>>>>>>>\n", professor.getFirstNameProf(),professor.getLastNameProf());
		System.out.println("You are added to the course/courses below:");
		//for (Professor p :AdminService.listAllProfessorById(professor.getIdProfessor())) {
			for (Courses c : AdminService.getCourseByProfessorId(professor.getIdProfessor()) ) {
				System.out.println(c.getCourseName());
			 }
	
		
		System.out.println("\n");
		System.out.println("Please write down the number to continue.");
		System.out.println("For your menu                    For Student                               For Course                  GO BACK");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                2-Add Student into course");
		System.out.println("                                3-Grade student");
		System.out.println("1-Change password               4-List student in ur course              7- Edit course details        8- GO BACK TO LOGIN PAGE." );
		System.out.println("                                5-See grade of student                   ");
		System.out.println("                                   with specific id                                                      ");
		System.out.println("                                6-See all grade-student                                 ");
		
		try {
		
		Scanner inputMenu =new Scanner (System.in);	
		System.out.println(" ");
		System.out.println(" > Write the number for the function you want to do next < \n");
	    int number = inputMenu.nextInt();
	    
        switch (number) {
		
		case 1:
			ChangePassword();
			break;
			
		case 2 :
			addStudentIntoActualCourse();
			break;
			
		case 3:
			new Menu().start();
			break;
		case 4 :
			break;
		case 5 :
			break;
		case 6 :
			break;
		case 7 :
			break;
		case 8 :
			break;
		default:
			System.out.println("Please write ONLY INTEGERS FROM 1-8. ");
			professorMenu(professor);
		}
		}catch (InputMismatchException e) {
			System.out.println("Please write ONLY INTEGERS FROM 1-8. ");
			professorMenu(professor);
		}
		
	
		
		
	}//end of professorMenu()
	
	public  void ChangePassword() {
		Scanner input = new Scanner (System.in);
		Professor prof = new Professor();
		
		System.out.println("Please enter your username: ");
		prof.setUsernameProf(input.next());
		System.out.println("Please enter your password: ");
		prof.setPasswordProf(input.next());
		
		try {
		AdminService.authenticateProfessor(prof);
		
		//System.out.println("Enter your new password: ");
		//prof.setPasswordProf(input.next());
		
		//ProfessorService.changePassword(prof.getUsernameProf(), prof.getPasswordProf());
		
		}catch (ProjectException exception) {
			System.out.println(">>>> "+exception.getMessage());
			ChangePassword();
		}
		finally {
			input.close();
		}
	}

public void addStudentIntoActualCourse() {
		
		System.out.println(" * ADD Student Category. * ");
		
		Scanner sc = new Scanner(System.in);
		Student student = new Student();
		Courses course = new Courses();
		Professor professor= new Professor();
		try {
			
			System.out.println("Please add student's details.");
			
			System.out.print("Please write course id in which you want to add the student. -->  ");
			
			course.setCourseId(sc.nextInt());
			
			System.out.print("Give name: ");
			student.setFirstNameStudent(sc.next());
			
			System.out.print("Give last name: "); 
			student.setLastNameStudent(sc.next());
			
			System.out.print("Give usernamename: ");
			student.setUserNameStudent(sc.next());
			
			System.out.print("Give password: ");
			student.setPasswordStudent(sc.next());
			
			
			ProfessorService.addStudent(student);
			ProfessorService.getIdStudentByUsername(student);
			
			for (Courses c : AdminService.getCourseByID(course.getCourseId())) {
            ProfessorService.addStudentCoursebyIds(student.getIdStudent(), course.getCourseId());
		    System.out.printf("<<<<<<<<<< You just added student %s %s with id %d. >>>>>>>>>>", student.getFirstNameStudent() , student.getLastNameStudent(),student.getIdStudent());
		    System.out.printf("into course %s",c.getCourseName());
			}
			professorMenu(professor);
		} catch (ProjectException exception) {
			System.out.println(">>>> "+exception.getMessage()+ "\nPlease try to add another student <<<<\n");
			
			addStudentIntoActualCourse();
		}
		finally {
			sc.close();
		}
		
}
}//end of CLASS
