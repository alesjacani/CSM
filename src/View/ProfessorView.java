package View;

import java.util.Scanner;

import Accounts.Professor;

public class ProfessorView {
	public void professorMenu() {
		System.out.println("\n");
        Professor professor = new Professor();
		System.out.println("WELCOME TO PROFESSOR MENU");
		System.out.println("\n");
		System.out.println("Please write down the number to continue.");
		
		System.out.println("1- Add students.");
		System.out.println("2- Grade students.");
		System.out.println("3- Go Back. \n");
       
		
		Scanner inputMenu =new Scanner (System.in);		
	    int number = inputMenu.nextInt();
	    
        switch (number) {
		
		case 1:
			break;
			
		case 2 :
			break;
			
		case 3:
			new Menu().start();
			break;
		
		default:
			System.out.println("Please write ONLY INTEGERS FROM 1-3. ");
			professorMenu();
		}
        
		
	}

}
