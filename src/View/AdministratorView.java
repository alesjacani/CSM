package View;

import java.awt.List;
import java.security.acl.LastOwnerException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Accounts.Administrator;
import Accounts.Professor;
import Accounts.Student;
import Accounts.User;
import Course.Courses;
import Exceptions.ProjectException;
import Service.CourseService;
import Service.ProfessorService;
import Service.StudentService;
//import exceptions.CustomException;



public class AdministratorView {
	  Administrator admin = new Administrator(1399, "Alesja", "Cani", "alesjacani", "ales123");
	 
	
	public void adminMenu() {
		System.out.println("\n");

		System.out.println("***  WELCOME TO ADMINISTRATOR PAGE "+ admin.getFirstNameAdmin() +"  "+ admin.getLastNameAdmin() +". ***");
		System.out.println("Type a number to continue.\n");
		
		System.out.println("For Course                    For Professor                              For Student                  GO BACK");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("1-Add Course                2- Add professor                           6- Edit student          9- GO BACK TO LOGIN PAGE." );
		System.out.println("                            3- Edit professor                          7- Delete student");
		System.out.println("                            4- Delete professor                        8- List students.");
		System.out.println("                            5- List professors.                                        ");
		System.out.println("\n");
	
		
		try {
			
		
		Scanner input = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" > Write the number for the function you want to do next < \n");
		int number = input.nextInt();
		
		switch (number) {
		case 1 :
			addCourse();
			break;
	    
		case 2 :
			break;
			
		case 3 :
			break;
			
		case 4 :
		
			break;
			
		case 5 :
		//	addStudent();
			break;
			
		case 6 :
			break;
			
		case 7 :
			break;
			
		case 8 :
			break;
			
		case 9:
			new Menu().start();
			break;
			
			default:
				System.out.println("******   Please write ONLY INTEGERS FROM 1-9.    ****** \n \n");
				adminMenu();
				//try ex ??????????
		}
} catch (InputMismatchException e) {
			System.out.println("Please write ONLY INTEGERS FROM 1-9. ");
			adminMenu();
		}
		
		
	}
	
	
	
	
	
	public void addCourse() {
		System.out.println("* ADD course category *");
		Scanner sc = new Scanner(System.in);
		Courses course = new Courses();
		try {
			System.out.println("Write the name of course.");
			course.setCourseName(sc.nextLine());
			CourseService.addCourse(course);
			CourseService.getCourseIdyName(course);
			
			System.out.printf ("You just add a new course with name %s with id %d ", course.getCourseName(), course.getCourseId());
			
			adminMenu();
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
		} finally {
			sc.close();
		}

	}//end of addcourse
	
	/*public void addProfessor(Courses c) {
		
		System.out.println(" * ADD Professor Category. * ");
		
		System.out.print("Please write in which course do you want to add the professor. -->  ");
		Scanner sc = new Scanner(System.in);
		
		c.setCourseName(sc.next());
		
		
		System.out.println("Please add professor's details.");
		Professor professor1 = new Professor();
		
		try {
			
			
			System.out.print("Give name: ");
			professor1.setFirstNameProf(sc.next());
			
			System.out.print("Give last name: "); 
			professor1.setLastNameProf(sc.next());
			
			System.out.print("Give usernamename: ");
			professor1.setUserNameProf(sc.next());
			
			System.out.print("Give password: ");
			professor1.setPasswordProf((sc.next()));
			
			
	//		ProfessorService.addProfessors(professor1, c);
	//		ProfessorService.addCourse(professor1, c);
		//	System.out.printf("<<<<<<<<<< You just added professor %s %s in %s course . >>>>>>>>>>", professor1.getFirstNameProf() , professor1.getLastNameProf(),c.getCourseName());
			
			
			 
		//	adminMenu(c);
			
	//	} 
	
		
		catch (ProjectException exception) {
			
			System.out.println("\n"+exception.getMessage());
			
			adminMenu(c);
		}
		finally {
			sc.close();
		}
	
		
		
		
}//end of addProfessor();*/
	
	/*
public void listProfessors(Courses course) {
	System.out.println(" ~List of professors~ \n ");
		for (Professor p : course.getProfessorList()) {
		System.out.printf("- %s %s with username: %s and password: %s. in %s course \n", p.getFirstNameProf(), p.getLastNameProf(), p.getUserNameProf(),p.getPasswordProf(),course.getCourseName());
			
		}
		adminMenu(course);
	}
	
	*/
	
	

	
}//end of CLASS