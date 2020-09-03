package View;

import java.util.Scanner;

public class StudentView {
	
	
	
public void studentMenu() {
		System.out.println("\n");
		System.out.println("WELCOME TO STUDENT MENU");
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
			studentMenu();
		}
        
		
	}

}
