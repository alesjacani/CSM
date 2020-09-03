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
import Service.ProfessorService;
import Service.StudentService;



public class AdministratorView {
	  Administrator admin  = new Administrator ("Alesja", "Cani", "alesjacani", "123");
	  
	  
	  
	public void AdminLoginView(Courses course) {
		
		Scanner input = new Scanner (System.in);
		System.out.println("\n| PLEASE LOGIN IN TO CONTINUE |  \n");
		
		System.out.print("Write your username: " );
		
		String userName = input.next();
		System.out.println("-------------------------------------------------------------");
		
		System.out.print("Write your password: ");
		String password = input.next();
		
		
		if (admin.getUserNameAdmin().equals(userName) && admin.getPasswordAdmin().equals(password)) {
			adminMenu(course);
		}
		else {    
			System.out.println(" ___________________________________________________________\n" +"|                                                           | \n"
					+ "| INCORRECT USERNAME OR PASSWORD.                           | \n| Please try again!                                         |\n"
					+ "|___________________________________________________________|\n");
			
			
			AdminLoginView(course);
		}
		input.close();
		}
	
	
	 

	
	
	
	public void adminMenu(Courses course) {
		System.out.println("\n");

		System.out.println("***  WELCOME TO ADMINISTRATOR PAGE "+ admin.getFirstNameAdmin() +"  "+ admin.getLastNameAdmin() +". ***");
		System.out.println("Type a number to continue.\n");
		
		System.out.println("For Professor                                 For Student                    GO BACK");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("1- Add professor                           5- Add student            9- GO BACK TO LOGIN PAGE." );
		System.out.println("2- Edit professor                          6- Edit student");
		System.out.println("3- Delete professor                        7- Delete student");
		System.out.println("4- List professors.                        8- List students. ");
		System.out.println("\n");
	
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" > Write the number for the function you want to do next < \n");
		int number = input.nextInt();
		
		switch (number) {
		case 1 :
			addProfessor(course);
			break;
	    
		case 2 :
			break;
			
		case 3 :
			break;
			
		case 4 :
		listProfessors(course);
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
				adminMenu(course);
				//try ex ??????????
		}
		
		
	}
	
	
	public void addProfessor(Courses c) {
		
		System.out.println(" * ADD Professor Category. * ");
		System.out.println("Please add professor's details.");
		
		Scanner sc = new Scanner(System.in);
		
		Professor professor1 = new Professor();
		
		try {
			
			
			System.out.print("Give name: ");
			professor1.setFirstNameProf(sc.next());
			
			System.out.print("Give last name: ");
			professor1.setLastNameProf(sc.next());
			
			System.out.print("Give usernamename: ");
			professor1.setUserNameProf((sc.next()));
			
			System.out.print("Give password: ");
			professor1.setPasswordProf((sc.next()));
			
			
			ProfessorService.addProfessors(professor1, c);
			
			System.out.println("<<<<<<<<<< You just added professor "+ professor1.getFirstNameProf() + " "+ professor1.getLastNameProf() + ". >>>>>>>>>>");
			
			
			 
			adminMenu(c);
			
		} 
	
		
		catch (ProjectException exception) {
			
			System.out.println("\n"+exception.getMessage());
			
			adminMenu(c);
		}
		finally {
			sc.close();
		}
		
		
		
		
}//end of addProfessor();
	
	
public void listProfessors(Courses course) {
	System.out.println(" ~List of professors~ \n ");
		for (Professor p : course.getProfessorList()) {
		System.out.printf("- %s %s with username: %s and password: %s.\n", p.getFirstNameProf(), p.getLastNameProf(), p.getUserNameProf(),p.getPasswordProf());
			
		}
		adminMenu(course);
	}
	
	
	
	

	
}//end of CLASS