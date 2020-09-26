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
import Service.AdminService;
import Service.ProfessorService;
import Service.StudentService;
import Util.Messages;

public class AdministratorView {
	  Administrator admin = new Administrator(1399, "Alesja", "Cani", "alesjacani", "ales123");
	 
	
	public void adminMenu() {
		System.out.println("\n");

		System.out.println("***  WELCOME TO ADMINISTRATOR PAGE "+ admin.getFirstNameAdmin() +"  "+ admin.getLastNameAdmin() +". ***");
		System.out.println("Type a number to continue.\n");
		
		System.out.println("For Course                    For Professor                              For Student                  GO BACK");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("1-Add Course                6- Add professor                           10- Edit student          13- GO BACK TO LOGIN PAGE." );
		System.out.println("2-Delete all Courses        7- Edit professor                          11- Delete student");
		System.out.println("3-View course details       8- Delete professor                        12- List students.");
		System.out.println("  with specific id          9- List professors.                                        ");
		System.out.println("4-List all courses                                                                   ");
		System.out.println("5-Delete course \nby specific id \n");
	
		
		try {
			Courses course = new Courses();
			Professor professor = new Professor();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" > Write the number for the function you want to do next < \n");
		int number = input.nextInt();
		
		switch (number) {
		case 1 :
			addCourse();
			break;
	    
		case 2 :
		deleteCourse();
			break;
			
		case 3 :
			getCoursebyId();
			break;
			
		case 4 :
		listAllCourses();
			break;
			
		case 5 :
		//deleteCoursebyId();
			break;
			
		case 6 :
			addProfessor();
			break;
			
		case 7 :
			break;
			
		case 8 :
			break;
			
		case 9:
		listAllProfessors();
			break;
		case 10:
			break;
		case 11:
		
			break;
		case 12:
			
			break;
		case 13:
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
	
		try {
			Courses course = new Courses();
			System.out.println("Write the name of course.");
			String coursename = sc.nextLine();
			course.setCourseName(coursename);
			AdminService.addCourse(course);
		    AdminService.getCourseIdyName(course);                            
			System.out.printf ("You just add a new course with name %s with id %d ", course.getCourseName(), course.getCourseId());
			adminMenu();
			/*for(Courses c : CourseService.listAllCourses()) {
					if (coursename.equalsIgnoreCase(c.getCourseName()))  { 
						System.out.println("This course already exists.");
						addCourse();	
					 }
					else {
					CourseService.addCourse(course);
				    CourseService.getCourseIdyName(course);                            
					System.out.printf ("You just add a new course with name %s with id %d ", course.getCourseName(), course.getCourseId());
							
							adminMenu();
							}
			}*/
		} catch (ProjectException exception) {//string merr dhe int ???????
			System.out.println(exception);
			addCourse();
		} finally {
			sc.close();
		}

	}//end of addcourse
	
	public void deleteCourse() {
		
		Scanner sc = new Scanner(System.in);
		try {
			Courses course =new Courses();
			
			AdminService.deleteCourse(course);
			
			System.out.printf("YOU DELETED ALL COURSES.");
	      
			adminMenu();
			
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
		} finally {
			sc.close();
		}

	}
	
	
	public void deleteCoursebyId(Courses course, Professor professor) {
		System.out.println("Give course's id to delete it:");
		Scanner sc = new Scanner(System.in);
		try {
		//	Courses course = new Courses();

			int courseId= sc.nextInt();
			course.setCourseId(courseId) ;
			for(Courses c : AdminService.listAllCourses()) {
				if (courseId== course.getCourseId()) {
					AdminService.deleteCourseById(c.getCourseId());
					System.out.printf("Course with name %s and id %d is deleted.", c.getCourseName(),c.getCourseId());
					adminMenu();
				}
				 System.out.println("THIS ID DOES NOT EXITS. TRY AGAIN.");
				deleteCoursebyId(course,professor);			
			}
			 
		}catch (InputMismatchException exception) {
	        System.out.println("Write only integers please.");
		    deleteCoursebyId(course,professor);
			
			}finally {
				sc.close();
			}
		
	}
	
	
	public void  getCoursebyId () {
		
		
		System.out.println("Give id to see the course details:");
		Scanner sc = new Scanner(System.in);
		
		try {
			Courses course =new Courses();
			
			int courseId= sc.nextInt();
			course.setCourseId(courseId) ;
			
			
				 for (Courses c : AdminService.getCourseByID(course.getCourseId())) {
				 if (courseId ==course.getCourseId()) {
						System.out.printf("The course with id %d has: \n -Name: %s \n -Description: %s , \n -Duration of %s \n -Teached by professor %s.", c.getCourseId(),c.getCourseName(),c.getDesciption(),c.getDurationTime(),c.getProfessorList());
						adminMenu();
					}
				 }
				 System.out.println("THIS ID DOES NOT EXITS. TRY AGAIN.");
				 getCoursebyId();			
				 
				 } catch (InputMismatchException exception) {
        System.out.println("Write only integers please.");
		getCoursebyId();
		
		} finally {
		sc.close();
		}
		
	}//end of getcoursebyid
	
	public void listAllCourses() {
		for (Courses c : AdminService.listAllCourses() ) {
			for (Professor p :AdminService.listAllProfessorById(c.getProfessorId())) {
System.out.printf("The course with id %d has: \n -Name: %s \n -Description: %s , \n -Duration of %s \n -Teached by professor %s. \n \n ", c.getCourseId(),c.getCourseName(),c.getDesciption(),c.getDurationTime(), p.getFirstNameProf());
			 }
		}
	adminMenu();
	}
	
	 
	public void listAllProfessors() {
		for (Professor p: AdminService.listAllProfessors()) {
			System.out.printf("\nProfessor %s %s , with username %s teaching in course/s:\n",p.getFirstNameProf(),p.getLastNameProf(),p.getUsernameProf());

			for (Courses c : AdminService.getCourseByProfessorId(p.getIdProfessor())) {
	//System.out.printf("\nProfessor %s %s , with username %s teaching in course/s:\n",p.getFirstNameProf(),p.getLastNameProf(),p.getUsernameProf());
				System.out.printf("-%s\n",c.getCourseName());
			}
		}
	}
	public void addProfessor() {
		
		System.out.println(" * ADD Professor Category. * ");
		

		Scanner sc = new Scanner(System.in);
		Professor professor = new Professor();
		Courses course = new Courses();
		try {
			
			System.out.println("Please add professor's details.");
			
			System.out.print("Please write in which course do you want to add the professor. -->  ");
			
			course.setCourseId(sc.nextInt());
			
			System.out.print("Give name: ");
			professor.setFirstNameProf(sc.next());
			
			System.out.print("Give last name: "); 
			professor.setLastNameProf(sc.next());
			
			System.out.print("Give usernamename: ");
			professor.setUsernameProf(sc.next());
			
			System.out.print("Give password: ");
			professor.setPasswordProf((sc.next()));
			
			
			AdminService.addProfessor(professor);
			AdminService.getProfIdbyUsername(professor);
			
			for (Courses c : AdminService.getCourseByID(course.getCourseId())) {

		
			AdminService.addProfIntoCourse(professor, course);
		
		    System.out.printf("<<<<<<<<<< You just added professor %s %s with id %d. >>>>>>>>>>", professor.getFirstNameProf() , professor.getLastNameProf(),professor.getIdProfessor());
		    System.out.printf("into course %s",c.getCourseName());
			adminMenu();
			 }
		} catch (ProjectException exception) {
			System.out.println(">>>> "+exception.getMessage()+ "\nPlease try to add another professor <<<<\n");
			
			addProfessor();
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