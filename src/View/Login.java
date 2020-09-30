package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import Accounts.Administrator;
import Accounts.Professor;
import Accounts.Student;
import Accounts.User;
import Course.Courses;
import Exceptions.ProjectException;
import Service.AdminService;
import Service.ProfessorService;
import View.AdministratorView;



public class Login {
	Administrator admin = new Administrator(1399, "Alesja", "Cani", "alesjacani", "ales123");
	  
	  
	public void adminView() {
		
		
		System.out.println("\n| PLEASE type 1 to LOGIN IN  |");
		System.out.println("| Type 2 to GO BACK  :)      |\n");
		

		Scanner input = new Scanner (System.in);
		
		try {
			int number = input.nextInt();
			switch (number) {
			case 1:
				adminLogin();
				break;
			case 2:
				new Menu().start();
			default:
				  System.out.println("  Please write ONLY INTEGERS FROM 1-2. \nLET'S START OVER & WRITE AGAIN.\n\n");
				adminView();
			}
		}
		   catch (InputMismatchException e) {
			   System.out.println("  Please write ONLY INTEGERS FROM 1-2.  \n");
			   adminView();
		}
		finally {
			input.close();}
		
		
	}
	
public void adminLogin() {
	System.out.println(" \n*Enter your details right to continue*\n");
	System.out.print("Write your username: " );
	Scanner input = new Scanner (System.in);
	
	String userName = input.next();
	System.out.println("-------------------------------------------------------------");
	
	System.out.print("Write your password: ");
	String password = input.next();
	
	
	if (admin.getUserNameAdmin().equals(userName) && admin.getPasswordAdmin().equals(password)) {
		
	   	new AdministratorView().adminMenu();

	}
	else {    
		System.out.println(" ___________________________________________________________\n" +"|                                                           | \n"
				+ "| INCORRECT USERNAME OR PASSWORD.                           | \n| Please try again!                                         |\n"
				+ "|___________________________________________________________|\n");

		adminLogin();
	}
	
	
	input.close();
	}


public void professorView() {

	System.out.println("\n| PLEASE type *1* to LOGIN IN  |");
	System.out.println("| Type *2* to GO BACK  :)      |\n");
	

	Scanner input = new Scanner (System.in);
	
	try {
		int number = input.nextInt();
		switch (number) {
		case 1:
			professorLogin();;
			break;
		case 2:
			new Menu().start();
		default:
			System.out.println("  Please write ONLY INTEGERS FROM 1-2. \nLET'S START OVER & WRITE AGAIN.\n\n");
			professorView();
		}
	}
	   catch (InputMismatchException e) {
		   System.out.println("  Please write ONLY INTEGERS FROM 1-2.  \n");
		   professorView();
	}
	finally {
		input.close();}
	
	

}//end of professorView

public void professorLogin() {
	Scanner input = new Scanner (System.in);
	Professor professor = new Professor();

	System.out.println(" \n*Enter your details right to continue*\n");
	
	System.out.print("Write your username: " );
	professor.setUsernameProf(input.next());
	
	
	System.out.println("-------------------------------------------------------------");
	
	System.out.print("Write your password: ");
	professor.setPasswordProf(input.next());
	
	try {
		AdminService.authenticateProfessor(professor);
    
		new ProfessorView().professorMenu(professor);
	
		
	} catch (ProjectException e) {
		System.out.println("\n"+e.getMessage()+"\n");
		professorLogin();
	}finally {
		input.close();}
	
	
	
}//end of professorLogin



public void StudentView() {
	
	
	System.out.println("\n| PLEASE type 1 to LOGIN IN  |");
	System.out.println("| Type 2 to GO BACK  :)      |\n");
	

	Scanner input = new Scanner (System.in);
	
	try {
		int number = input.nextInt();
		switch (number) {
		case 1:
			studentLogin();
			break;
		case 2:
			new Menu().start();
		default:
			  System.out.println("  Please write ONLY INTEGERS FROM 1-2. \nLET'S START OVER & WRITE AGAIN.\n\n");
			StudentView();
		}
	}
	   catch (InputMismatchException e) {
		   System.out.println("  Please write ONLY INTEGERS FROM 1-2.  \n");
		   StudentView();
	}
	finally {
		input.close();}
	
	
}



public void studentLogin() {
	Scanner input = new Scanner (System.in);
	Student student = new Student();

	System.out.println(" \n*Enter your details right to continue*\n");
	
	System.out.print("Write your username: " );
	student.setUserNameStudent(input.next());
	
	
	System.out.println("-------------------------------------------------------------");
	
	System.out.print("Write your password: ");
	student.setPasswordStudent(input.next());
	
	try {
		ProfessorService.authenticateStudent(student);
    
		new StudentView().studentMenu(student);
	
		
	} catch (ProjectException e) {
		System.out.println("\n"+e.getMessage()+"\n");
		professorLogin();
	}finally {
		input.close();}
	
	
	
}//end of professorLogin

}//end of Class

