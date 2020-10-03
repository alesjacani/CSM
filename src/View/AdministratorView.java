package View;

import java.util.List;
import java.security.acl.LastOwnerException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Accounts.Administrator;
import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Exceptions.ProjectException;
import Repository.UsersRepository;
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
		
		System.out.println("For Course                    For Professor                       GO BACK");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("1-Add Course                7- Add professor                 11- GO BACK TO LOGIN PAGE." );
		System.out.println("2-Delete all Courses        8- Edit professor                      ");
		System.out.println("3-View course details       9- Delete by username                  ");
		System.out.println("  by Course Name            10-List all professors.                                              ");
		System.out.println("4-List all courses                                                              ");
		System.out.println("5-Delete course \nby course name                                      ");
	    System.out.println("6-Add a actual professor\n into a course.");
		
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
			getCoursebyName();
			break;
			
		case 4 :
		    listAllCourses();
			break;
			
		case 5 :
		    deleteCoursebyName();
			break;
			
		case 6 :
			addActualProfessorIntoCourseByIDs();
			break;
			
		case 7 :
			addProfessorIntoActualCourse();
			break;
			
		case 8 :
			editProfessorDetails();
			break;
			
		case 9:
		   deleteProfessorByUsername();
			break;
		case 10:
			listAllProfessors();
			break;
		
        case 11:
        	new Menu().start();
			break;
 
		default:
				System.out.println("******   Please write ONLY INTEGERS FROM 1-11.    ****** \n \n");
				adminMenu();
				//try ex ??????????
		}
} catch (InputMismatchException e) {
			System.out.println("Please write ONLY INTEGERS FROM 1-11. ");
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
		   Courses c=  AdminService.getCourseByName(course.getCourseName());
		  
			System.out.printf ("You just add a new course with name %s with id %d ", course.getCourseName(), c.getCourseId());
			adminMenu();

        } catch (ProjectException exception) {
			System.out.printf(" \n %s \n\n",exception.getMessage());
			addCourse();
		} catch (InputMismatchException exception) {
	        System.out.println("\"Write word OR number in  requested details please.\n");
		    addCourse();
		}finally {
			sc.close();
		}
}//end of addcourse
	
	
	
	public void deleteCourse() {
		Scanner sc = new Scanner(System.in);
		try {
			AdminService.deleteCourse();
			System.out.printf("YOU DELETED ALL COURSES.");
	      adminMenu();
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
		} finally {
			sc.close();
		}

	}
	
	
	
	public void deleteCoursebyName() {
		Scanner sc = new Scanner(System.in);
		Courses course=new Courses();
		try {
	        
			System.out.println("Please give course name: ");
			
			course.setCourseName(sc.next()) ;
		    Courses c = AdminService.authenticateCourse(course);
		     
			AdminService.deleteCourseByName(c);
			System.out.println("Course was successfully deleted!");
			adminMenu();
				
		}catch (InputMismatchException exception) {
	        System.out.println("\"Write word OR number in  requested details please.\n");
		    deleteCoursebyName();
		}catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			 deleteCoursebyName();
		}finally {
				sc.close();
			}
	}
	
	
	
	public void  getCoursebyName () {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Courses course =new Courses();
			System.out.print("Give course NAME:");
			
			course.setCourseName(sc.next()) ;
			
			
			Courses c = AdminService.authenticateCourse(course);
			
            Professor p= ProfessorService.getProfByCourseName(course.getCourseName());
	        System.out.printf("The course with id %d has: \n -Name: %s \n -Description: %s , \n -Duration of %s \n -Teached by professor %s %s.", 
	        		          c.getCourseId(),c.getCourseName(),c.getDesciption(),c.getDurationTime(),p.getFirstNameProf(),p.getLastNameProf());
	        adminMenu();
	
		}catch (InputMismatchException exception) {
        System.out.println("Write word OR number in  requested details please.\n");
		getCoursebyName();
		}catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			getCoursebyName();
		}finally {
		sc.close();
		}
		
	}//end of getcoursebyid
	
	
	
	
	
	public void listAllCourses() {
		for (Courses c : AdminService.listAllCourses() ) {
			
		System.out.printf("\nThe course with id %d has: \n -Name: %s \n -Description: %s , \n -Duration of %s \n -Teached by professor %s %s. \n -List of students: \n",
		                c.getCourseId(),c.getCourseName(),c.getDesciption(),c.getDurationTime(),c.getProfessorList().get(0).getFirstNameProf(),
		             c.getProfessorList().get(0).getLastNameProf() ); 
		 //for(Courses course: AdminService.getCourseByProfessorUsername(c.getProfessorList().get(0).getUsernameProf())) {
	    //	System.out.printf("%s %s \n",course.getStudentList().get(0).getFirstNameStudent(),course.getStudentList().get(0).getLastNameStudent());
		for(Student s :AdminService.listStudentByCourseName(c.getCourseName())) {
			System.out.printf("  %s %s\n",s.getFirstNameStudent(),s.getLastNameStudent());
			
	    }
}
		adminMenu();
}
	 
	
	public void listAllProfessors() {
		for (Professor p: AdminService.listAllProfessors()) {
			System.out.printf("\nProfessor %s %s , with username %s teaching in course/s:\n",
					         p.getFirstNameProf(),p.getLastNameProf(),p.getUsernameProf());

			for (Courses c : AdminService.getCourseByProfessorUsername(p.getUsernameProf())) {
	//System.out.printf("\nProfessor %s %s , with username %s teaching in course/s:\n",p.getFirstNameProf(),p.getLastNameProf(),p.getUsernameProf());
				System.out.printf("-%s\n",c.getCourseName());
			}
		}
		adminMenu();
	}
	
	
	
	public void addProfessorIntoActualCourse() {
		System.out.println(" * ADD Professor Category. * ");
		Scanner sc = new Scanner(System.in);
		Professor professor = new Professor();
		Courses course = new Courses();
		try {
			
			System.out.println("Please add professor's details.\n");
			
			
			System.out.print("Please write in which course NAME do you want to add the professor. -->");
			course.setCourseName(sc.next());
			
			
			
			AdminService.authenticateCourse(course);
		    AdminService.courseHasProf(course);
			
		    
		    
		    System.out.print("Give name: ");
			professor.setFirstNameProf(sc.next());
			
			System.out.print("Give last name: "); 
			professor.setLastNameProf(sc.next());
			
			System.out.print("Give usernamename: ");
			professor.setUsernameProf(sc.next());
			
			System.out.print("Give password: ");
			professor.setPasswordProf((sc.next()));
			
			//AdminService.authenticateProfessorByUsername(professor);
			
			AdminService.addProfessor(professor,course);
			
			
			
		    System.out.printf("<<<<<<<<<< You just added professor %s %s with id %d ", professor.getFirstNameProf() , professor.getLastNameProf(),professor.getIdProfessor());
		    System.out.printf("into course %s . >>>>>>>>>>",course.getCourseName());
			adminMenu();
			 
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			addProfessorIntoActualCourse();
		}finally {
			sc.close();
		}
		
}//end of addProfessor()
	
	
	
	public void addActualProfessorIntoCourseByIDs() {
		
		Scanner input = new Scanner (System.in);
		Professor professor = new Professor();
		Courses course = new Courses();
		try {
			
			System.out.print("Give the professor id: ");
			professor.setIdProfessor(input.nextInt());
			///authh
			//System.out.print("Give the course id you want to add the professor: ");
			//course.setCourseId(input.nextInt());
			System.out.print("Give the course Name you want to add the professor: ");
			course.setCourseName(input.next());
			AdminService.authenticateCourse(course);
			Courses c= AdminService.getCourseByName(course.getCourseName());
		    AdminService.courseHasProf(course);
		    
	
			AdminService.addProfIntoCourse(professor, c); //me id
				
		    Professor p=ProfessorService.getProfByCourseName(course.getCourseName());
				
		   
				
			System.out.printf("You just added professor: %s %s into %s course.",p.getFirstNameProf(),p.getLastNameProf(),course.getCourseName());
		
			adminMenu();
		} catch (ProjectException exception) {
			System.out.printf("\n%s\n\n",exception.getMessage());
			addActualProfessorIntoCourseByIDs();
		}catch (InputMismatchException exception) {
	        System.out.println("Write word OR number in  requested details please.\n");
	        addActualProfessorIntoCourseByIDs();
			}finally {
				input.close();
			}
		
		
		
		
		
	//	for (Professor p :AdminService.listAllProfessorById(professor.getIdProfessor())) {
			/*for (Courses c : AdminService.getCourseByID(course.getCourseId()) ) {
System.out.printf("You just added professor: %s %s into %s course.",p.getFirstNameProf(),p.getLastNameProf(),c.getCourseName());

			 }*/
	//	}
		
	}//end
	
	//akoma pa ia dhen te dhenat profes, nqs ka ber ndonje gabim ne te shkruar.
	
	public void editProfessorDetails() {
		Scanner sc =new Scanner(System.in);
		Professor professor = new Professor();
		try {
			System.out.println("Enter username for the professor you want to edit: ");
			
			professor.setUsernameProf(sc.next());
			
			System.out.println("Enter the professor password you want to edit: ");
			professor.setPasswordProf(sc.next());
			
			AdminService.authenticateProfessor(professor);
			
			System.out.println("ENTER NEW NAME: ");
			professor.setFirstNameProf(sc.next());
			
			System.out.println("ENTER NEW SURNAME:");
			professor.setLastNameProf(sc.next());
			
			System.out.println("ENTER NEW USERNAME");
			professor.setUsernameProf(sc.next());
			
			AdminService.editProfessorDetails(professor);
			
			System.out.println("You succeffully just edited professor details.");
			adminMenu();
			
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			editProfessorDetails();
		}catch (InputMismatchException exception) {
	        System.out.println("Write word OR number in  requested details please.\n");
			editProfessorDetails();
			}finally {
				sc.close();
			}
		
		
		
	}
	
	
	
	public void deleteProfessorByUsername() {
		Scanner sc = new Scanner(System.in);
		Professor professor =new Professor();
		try {
	        
			System.out.println("Please give professor username: ");
			professor.setUsernameProf(sc.next());;
			
		    
			AdminService.deleteProfessorByUsername(professor);
		    
		    System.out.println("Professor was successfully deleted!");
			adminMenu();
				
		}
		catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			deleteProfessorByUsername();
		}finally {
				sc.close();
			}
}
	
	



	
}//end of CLASS