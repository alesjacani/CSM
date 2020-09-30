package View;

import java.util.Scanner;

import Accounts.Student;
import Course.Courses;
import Service.AdminService;

public class StudentView {
	
	
	
public void studentMenu(Student student) {
		System.out.println("\n");
		System.out.println("WELCOME TO STUDENT MENU");
		
		System.out.println("\n");

		System.out.printf("<<<<<<<<<< WELCOME TO STUDENT MENU %S %S  >>>>>>>>>>\n", student.getFirstNameStudent(),student.getLastNameStudent());
		System.out.println("You are added to the course/courses  below:");
		//for (Professor p :AdminService.listAllProfessorById(professor.getIdProfessor())) {
			//for (Courses c : AdminService.getCourseByProfess) ) {
			//	System.out.printf("-%s   with id  %d.\n",c.getCourseName(),c.getCourseId());
			// }
	
		System.out.println("\n");
		System.out.println("Please write down the number to continue.");
		
		System.out.println("1- View the course and the grades."); // hap , select kurset qe ben pjese dhe shef notat
		System.out.println("2- Go Back. \n");
       
		
		Scanner inputMenu =new Scanner (System.in);		
	    int number = inputMenu.nextInt();
	    
        switch (number) {
		
		case 1:
			break;
			
		case 2 :
			new Menu().start();
			break;
			
		
		
		default:
			System.out.println("Please write ONLY INTEGERS FROM 1-2. ");
			studentMenu(student);
		}
        
		
	}

}
