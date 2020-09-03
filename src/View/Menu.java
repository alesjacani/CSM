package View;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Course.Courses;



public class Menu {

	
	public void start() {
		Courses course1 = new Courses("Math.");
		
		
		System.out.println(" ~ Welcome everybody! :) ~ \n");
		System.out.println("Please write down the number to continue of whatever menu you want.");
		System.out.println("1- Go to ADMINISTRATOR MENU");
		System.out.println("2- Go to PROFESSOR MENU");
		System.out.println("3- Go to STUDENT MENU\n");
		
		Scanner inputMenu =new Scanner (System.in);		
	    
	 
		
	  
	   try {
		   int number = inputMenu.nextInt();
		   switch (number) {

		   case 1:
		   	new AdministratorView().AdminLoginView(course1);
		   	break;
		   	
		   case 2 :
		   	new ProfessorView().professorMenu();
		   	break;
		   	
		   case 3:
		   	new StudentView().studentMenu();
		   	break;
		   	
		   default:
			    System.out.println("  Please write ONLY INTEGERS FROM 1-3. \nLET'S START OVER & WRITE AGAIN.\n\n");
			   //JOptionPane.showMessageDialog(null, "Please write ONLY INTEGERS FROM 1-3.");
               start();			   
	}  
		    
	   }
	   catch (InputMismatchException e) {
		   System.out.println("  Please write ONLY INTEGERS FROM 1-3.  \n");
		   start();
		   
	   }finally {
		   inputMenu.close();
	   }
	   	    	
	}
}//end of CLASS
		
	 
      
