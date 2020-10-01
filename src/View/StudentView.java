package View;

import java.util.InputMismatchException;
import java.util.Scanner;

import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Exceptions.ProjectException;
import Service.AdminService;
import Service.ProfessorService;

public class StudentView {
	
	
	
public void studentMenu(Student student) {
		System.out.println("\n");
		System.out.println("WELCOME TO STUDENT MENU");
		
		System.out.println("\n");

		System.out.printf("<<<<<<<<<< WELCOME TO STUDENT MENU %S %S  >>>>>>>>>>\n", student.getFirstNameStudent(),student.getLastNameStudent());
		System.out.println("You are added to the course/courses  below:");
		
			for (Courses c : AdminService.listCourseByStudentUsername(student.getUserNameStudent()))  {
		System.out.printf("-%s \n",c.getCourseName());
		 }
	
		System.out.println("\n");
		System.out.println("Please write down the number to continue.");
		
		System.out.println("1- View the course and the grades."); // hap , select kurset qe ben pjese dhe shef notat
		System.out.println("2- Go Back. \n");
       
		try {
		Scanner inputMenu =new Scanner (System.in);		
	    int number = inputMenu.nextInt();
	    
        switch (number) {
		
		case 1:
			viewCourseGrade();
			break;
			
		case 2 :
			new Menu().start();
			break;
			
		
		
		default:
			System.out.println("Please write ONLY INTEGERS FROM 1-2. ");
			studentMenu(student);
		}
        
		}catch (InputMismatchException e) {
			System.out.println("Please write ONLY INTEGERS FROM 1-2. ");
			studentMenu(student);
		}
	}


public void viewCourseGrade() {
	Scanner sc = new Scanner (System.in);
	Student student= new Student();
	
	System.out.print("Write your username: ");
	student.setUserNameStudent(sc.next());
    
    
    
	try {
		ProfessorService.authenticateStudent(student);
		System.out.println("Course/s        Professor         Your grade");
		System.out.println("--------------------------------------------");
		for (Courses c: AdminService.listCourseByStudentUsername(student.getUserNameStudent())) {
			Student s=AdminService.getAllStudentByUsername(student.getUserNameStudent());
			Professor p = ProfessorService.getProfByCourseName(c.getCourseName());
			int grade = ProfessorService.getGradeByStudentCourseId(s.getIdStudent(), c.getCourseId());
			System.out.printf("%s            %s %s      %d\n",c.getCourseName(),p.getFirstNameProf(),p.getLastNameProf(),grade);
		}
	} catch (ProjectException exception) {
		System.out.println(exception.getMessage());
		viewCourseGrade();
	}
}


}//end of Class 
