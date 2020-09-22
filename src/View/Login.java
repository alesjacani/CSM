package View;

import java.util.Scanner;

import Accounts.Administrator;
import Accounts.Professor;
import Accounts.Student;
import Accounts.User;
import Course.Courses;
import Exceptions.ProjectException;
import View.AdministratorView;



public class Login {
	Administrator admin = new Administrator(1399, "Alesja", "Cani", "alesjacani", "ales123");
	  
	  
	public void AdminLoginView() {
		
		Scanner input = new Scanner (System.in);
		System.out.println("\n| PLEASE LOGIN IN TO CONTINUE |  \n");
		
		System.out.print("Write your username: " );
		
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
			
			
			AdminLoginView();
		}
		input.close();
		}
	
	
	
}
